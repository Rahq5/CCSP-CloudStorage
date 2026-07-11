//this code is mimiking how should the controller recevie the files from user using I\O streams and file\paths construction methods
// YOU'RE NOT ALLOWED TO COPY PASTE IT'S JUST FOR TUTORIAL 

//---------------------------------------------Controller------------------------------------------------------------------------
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;

@RestController
public class StreamController {

    private final FileStreamService fileStreamService;

    // Inject the service layer component
    public StreamController(FileStreamService fileStreamService) {
        this.fileStreamService = fileStreamService;
    }

    @PostMapping("/api/stream-service")
    public String upload(HttpServletRequest request) throws Exception {
        
        String hardcodedPath = "/var/storage/ccsp_files/service_output.bin";

        // Extract the raw connection input stream reference from the HTTP socket
        try (InputStream networkStream = request.getInputStream()) {
            
            // Delegate the actual heavy lifting (the reading/writing loop) to the service
            fileStreamService.streamToDisk(networkStream, hardcodedPath);
        }

        return "Successfully processed via Service layer.";
    }
}



//----------------------------------------------Service-----------------------------------------------------------------------
import org.springframework.stereotype.Service;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class FileStreamService {

    private static final int BUFFER_SIZE = 8192; // 8KB RAM footprint

    public void streamToDisk(InputStream incomingDataStream, String targetPath) throws IOException {
        
        // Open disk writers based on the target path string passed by the controller
        try (FileOutputStream fileOutputStream = new FileOutputStream(targetPath);
             BufferedOutputStream diskStream = new BufferedOutputStream(fileOutputStream, BUFFER_SIZE)) {

            byte[] dataBuffer = new byte[BUFFER_SIZE];
            int bytesRead;

            // Execute the loop directly on the passed stream reference
            while ((bytesRead = incomingDataStream.read(dataBuffer)) != -1) {
                diskStream.write(dataBuffer, 0, bytesRead);
            }
            
            diskStream.flush();
        }
    }
}
