// package com.solidsudogear.ccsp.Controller;

// import java.io.File;
// import java.io.IOException;
// import java.io.InputStream;
// import java.io.OutputStream;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.nio.file.Paths;
// import java.nio.file.StandardOpenOption;
// import java.util.UUID;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.MediaType;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.multipart.MultipartFile;

// import com.solidsudogear.ccsp.Model.FileInfo;
// import org.springframework.web.bind.annotation.GetMapping;


// @RestController
// @RequestMapping("/file")
// public class UploadExpController {

//     @Value("${upload.dir}")
//     private String uploadDir;

//     @Value("${buffer.size.upload}")
//     private int BufferSize;
    
// /*

//     ### parameters defining: ####

//     - "consumes": what type of data is being recevied(it's receving a file now)
    
//     - "producing": what type of data will be returned to you (it's JSON file now)
    
// */
//     @PostMapping(value="/getfileInformation", consumes =MediaType.MULTIPART_FORM_DATA_VALUE, produces =MediaType.APPLICATION_JSON_VALUE )
//     public ResponseEntity<?> getFileData(@RequestParam("file")MultipartFile userFile){
        
//         FileInfo fileInfo = new FileInfo();

//         fileInfo.setFileName(userFile.getOriginalFilename());
//         fileInfo.setFileName(userFile.getName());
//         fileInfo.setFileLength(userFile.getSize());
//         fileInfo.setContentType(userFile.getContentType());
//         fileInfo.setReadable(userFile.getResource().isReadable());
//         fileInfo.setIsFileEmpty(userFile.isEmpty());

//         return new ResponseEntity<FileInfo>(fileInfo, HttpStatus.ACCEPTED);

//     }

//     // @GetMapping("/Hello")
//     // public String greetings() {
//     //     return "hello";
//     // }

//     // uploading files using .getBytes() chokes the RAM on high volumes 
//     @PostMapping(value="/upload")
//     public ResponseEntity<?> uploadFile(@RequestParam("file")MultipartFile file){
       
//         // making directory and saving it to files object so each file know where to be saved  
//         // String dir ="uploads";
//         File directory = new File(uploadDir);

//         // check if file exist
//         if(!directory.exists()){
//             directory.mkdir();
//         }

//         // try-catch block in case of any file uploading failure , if failed it prints the problem and returns badRequest
//         try {

//             String filename = UUID.randomUUID()+ "  "+file.getOriginalFilename();
//             Path filePath =  Paths.get(uploadDir +File.separator+ filename);
//             Files.write(filePath , file.getBytes());
//             return ResponseEntity.ok("file is uploaded and stored:" + file.getOriginalFilename());

//         } catch (IOException e) {
//           e.printStackTrace();
//           return ResponseEntity.badRequest().build();
//         }
        

//     }


//     // this uploads using streamingIO 
//     @PostMapping(value="/uploadStream")
//     public ResponseEntity<String> uploadUsingStream(@RequestParam("file") MultipartFile file){
        
//         // check if file is empty 
//         if (file.isEmpty()){
//             return ResponseEntity.badRequest().body("file is empty");
//         }

//         // create path to destination 
//         Path filePath = Paths.get(uploadDir +File.separator+file.getOriginalFilename());


//         try(
//             // establish stream input to the received file and output to destination
//             InputStream inputStream = file.getInputStream();
//             OutputStream outputStream = Files.newOutputStream(filePath, StandardOpenOption.CREATE);

//         ){
//              // declare the byte counter and loop of reading the bytes
//             byte[] Buffer = new byte[BufferSize];
//             int bytesRead; 

//             while((bytesRead = inputStream.read(Buffer) ) !=-1 ){
//                 outputStream.write(Buffer, 0, bytesRead);
//             }

//             return ResponseEntity.ok("file has uploaded successfully: "+ file.getOriginalFilename());

//         }catch(IOException e){

//             // place exciption for IOExeption in case of reading\writing error showing message 
//             return ResponseEntity.internalServerError().body("uploading failed: "+e.getMessage());
//         }

        
//     }
  
    
// }
