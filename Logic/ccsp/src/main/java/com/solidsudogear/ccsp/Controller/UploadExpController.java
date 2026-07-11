package com.solidsudogear.ccsp.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.solidsudogear.ccsp.Model.FileInfo;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/file")
public class UploadExpController {
    
/*

    ### parameters defining: ####

    - "consumes": what type of data is being recevied(it's receving a file now)
    
    - "producing": what type of data will be returned to you (it's JSON file now)
    
*/
    @PostMapping(value="/getfileInfo", consumes =MediaType.MULTIPART_FORM_DATA_VALUE, produces =MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<?> getFileData(@RequestParam("file")MultipartFile userFile){
        
        FileInfo fileInfo = new FileInfo();

        fileInfo.setFileName(userFile.getOriginalFilename());
        fileInfo.setFileName(userFile.getName());
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
    
    
}
