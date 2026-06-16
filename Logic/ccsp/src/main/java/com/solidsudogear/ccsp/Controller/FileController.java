package com.solidsudogear.ccsp.Controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {
    

    @GetMapping
    public ResponseEntity<String> confirming(){
        return ResponseEntity.ok("request arrived");
    }

    @PostMapping("/add")
    public ResponseEntity<?> addFile(@RequestParam("name")String name , @RequestParam("file") MultipartFile file){

        try{

            if(!file.isEmpty()){
                byte[] bytes = file.getBytes();
                return new ResponseEntity<>("the file"+name+ "has uploaded successfully",HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>("the file"+name+ " failed to uploaded! ",HttpStatus.BAD_REQUEST);
            }

        } catch(IOException e){
            System.err.println("### uploading failed!!\n");

            return new ResponseEntity<>("#### Fuck You nigger ",HttpStatus.CONFLICT);
        }

    }
}


/*

take the code
fetch from daatabase
put data on {} 
*/

