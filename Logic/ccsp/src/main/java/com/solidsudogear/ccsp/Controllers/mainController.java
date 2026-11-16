package com.solidsudogear.ccsp.Controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.solidsudogear.ccsp.Entity.FileInfo;
import com.solidsudogear.ccsp.Service.uploadingService;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/file")
public class mainController  {

    @Value("${upload.dir}")
    private String uploadDir;

    @Value("${buffer.size.upload}")
    private int BufferSize;

    @Autowired
    private uploadingService uploadingService;
    
/*

    ### parameters defining: ####

    - "consumes": what type of data is being recevied(it's receving a file now)
    
    - "producing": what type of data will be returned to you (it's JSON file now)
    
*/

    // this takes out file info from the file got sent via post 
    @PostMapping(value="/getfileInfo", consumes =MediaType.MULTIPART_FORM_DATA_VALUE, produces =MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<?> getFileData(@RequestParam("file")MultipartFile userFile){
        
        FileInfo fileInfo = new FileInfo();

        fileInfo.setFileName(userFile.getOriginalFilename());
        fileInfo.setFileLength(userFile.getSize());
        fileInfo.setContentType(userFile.getContentType());
        fileInfo.setReadable(userFile.getResource().isReadable());
        fileInfo.setIsFileEmpty(userFile.isEmpty());

        return new ResponseEntity<FileInfo>(fileInfo, HttpStatus.ACCEPTED);

    }

    //  just to confirm connections 
    @GetMapping
    public ResponseEntity<String> confirming(){
        return ResponseEntity.ok("request arrived");
    }


    // this uploads using streamingIO 
    @PostMapping(value="/upload")
    public ResponseEntity<String> addFile_uploadUsingStream(@RequestParam("file") MultipartFile file){
        
       
        String filename = uploadingService.uploading(file);
        return ResponseEntity.ok("file has uploaded successfully: "+ filename);
        

    }

    // this saves file via getBytes
    @PostMapping("/add")
    public ResponseEntity<?> addFile_getBytes(@RequestParam("name")String name , @RequestParam("file") MultipartFile file){

        try{

            if(!file.isEmpty()){
                // this is what am avoiding!
                // it will load instantly in the ram which gonna choke it  
                byte[] bytes = file.getBytes();
                
                return new ResponseEntity<>("the file"+name+ "has uploaded successfully",HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>("the file"+name+ " is empty! ",HttpStatus.BAD_REQUEST);
            }

        } catch(IOException e){
            System.err.println("### uploading failed!!\n");

            return new ResponseEntity<>("#### Fuck You nigger ",HttpStatus.CONFLICT);
        }

    }

    // uploading files using .getBytes() chokes the RAM on high volumes 
    @PostMapping(value="/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file")MultipartFile file){
       


        // making directory and saving it to files object so each file know where to be saved  
        // String dir ="uploads";
        File directory = new File(uploadDir);

        // check if file exist
        if(!directory.exists()){
            directory.mkdir();
        }

        // try-catch block in case of any file uploading failure , if failed it prints the problem and returns badRequest
        try {

            //makes a ID+fileName 
            String filename = UUID.randomUUID()+ "  "+file.getOriginalFilename();

            // gets the path and getting ready to upload 
            Path filePath =  Paths.get(uploadDir +File.separator+ filename);

            //uploading using getBytes() which is the one chokes the ram 
            Files.write(filePath , file.getBytes());
            return ResponseEntity.ok("file is uploaded and stored:" + file.getOriginalFilename());

        } catch (IOException e) {
          e.printStackTrace();
          return ResponseEntity.badRequest().build();
        }
        

    }
  
    
}
