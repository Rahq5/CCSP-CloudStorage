package com.solidsudogear.ccsp.Controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

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

    @GetMapping("/Hello")
    public String greetings() {
        return "hello";
    }


    // this uploads using streamingIO 
    @PostMapping(value="/upload")
    public ResponseEntity<String> uploadUsingStream(@RequestParam("file") MultipartFile file){
        
       
        String filname = uploadingService.uploading(file);
        return ResponseEntity.ok("file has uploaded successfully: "+ filname);
        

    }
  
    
}
