package com.solidsudogear.ccsp.ServiceImp;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.solidsudogear.ccsp.Exceptions.EmptyFileException;
import com.solidsudogear.ccsp.Exceptions.FileStorageException;
import com.solidsudogear.ccsp.Repositories.FileRepo;
import com.solidsudogear.ccsp.Service.uploadingService;


@Service
public class uploadingServiceImp implements uploadingService {

    @Value("${upload.dir}")
    private String uploadDir;

    @Value("${buffer.size.upload}")
    private int BufferSize;

    private FileRepo fileRepo;

    @Override
    public String uploading (MultipartFile file) {
       

        // check if file is empty 
        if (file.isEmpty()){
           throw new EmptyFileException("file is empty");
        }

        // create path to destination 
        Path filePath = Paths.get(uploadDir +File.separator+file.getOriginalFilename());
        
        try(
            // establish stream input to the received file and output to destination
            InputStream inputStream = file.getInputStream();
            OutputStream outputStream = Files.newOutputStream(filePath, StandardOpenOption.CREATE);

        ){
             // declare the byte counter and loop of reading the bytes
            byte[] Buffer = new byte[BufferSize];
            int bytesRead; 

            while((bytesRead = inputStream.read(Buffer) ) !=-1 ){
                outputStream.write(Buffer, 0, bytesRead);
            }


            // you need a mapper here 
            //fileRepo.save(file);

            return file.getOriginalFilename();

        } catch(IOException e){
            throw new FileStorageException("Failed to store file: " + e.getMessage(), e);
 
        }



    }
    
}
