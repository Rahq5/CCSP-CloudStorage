package com.solidsudogear.ccsp.ServiceImp;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.solidsudogear.ccsp.DTOs.FileDto;
import com.solidsudogear.ccsp.Entity.FileMetaData;
import com.solidsudogear.ccsp.Exceptions.EmptyFileException;
import com.solidsudogear.ccsp.Exceptions.FileStorageException;
import com.solidsudogear.ccsp.Repositories.FileRepo;
import com.solidsudogear.ccsp.Service.uploadingService;


// to fix:

/*
  1.    The streaming path doesn't create the directory. 
        /uploadByte in the controller calls mkdir(); 
        uploading() goes straight to Files.newOutputStream(...), so a missing upload.dir throws.

 2.  No @Transactional on the service method, even though it saves an entity.

 3. file path should be obtained AFTER the upload success

 4. User fingerprint should be labled on the file and authenticated if other user requested it 

*/




@Service
public class uploadingServiceImp implements uploadingService {

    @Value("${upload.dir}")
    private String uploadDir;

    @Value("${buffer.size.upload}")
    private int BufferSize;

    @Autowired 
    private FileRepo fileRepo;

    // our lovely helpless mapper
    @Autowired 
    private ModelMapper modelMapper;


    public uploadingServiceImp(FileRepo fileRepo , ModelMapper modelMapper){
        this.fileRepo = fileRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public String uploading (MultipartFile file) {

    

        // check if file is empty 
        if (file.isEmpty()){
           throw new EmptyFileException("file is empty");
        }

        // helper method to map the rest of the fields
        FileDto fileDto = fileDto_to_fileMetaData(file);

        // to make the path in order to select the path of outputStream
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
            

            FileMetaData fileMetaData = modelMapper.map(fileDto,FileMetaData.class);

            // you need a mapper here 
            fileRepo.save(fileMetaData);

            return file.getOriginalFilename();

        } catch(IOException e){
            throw new FileStorageException("Failed to store file: " + e.getMessage(), e);
 
        }

    }

    // fills the rest of the fields cuz multipart info is missing some fields
    public FileDto fileDto_to_fileMetaData(MultipartFile file){


        //multipart to dto includes only (name, size, content type)
        FileDto fileDto = modelMapper.map(file, FileDto.class);

        // create path to destination 
        Path filePath = Paths.get(uploadDir +File.separator+file.getOriginalFilename());

        //set uploaded date and modified also
        fileDto.setUploadedDate(LocalDate.now().toString());
        fileDto.setModifiedDate(LocalDate.now().toString());

        //WARNING: this should be obtained AFTER it get uploaded not before (force sure place)
        fileDto.setFilePath(filePath.toString());

        //change the name to the client side (the real file name)
        fileDto.setFileName(file.getOriginalFilename());

        return fileDto;
    }
    
}
