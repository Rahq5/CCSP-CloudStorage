package com.solidsudogear.ccsp.DTOs;

import lombok.Data;

@Data 
public class FileDto {


    private Long UserID;
    private String fileName;
    private String contentType;
    private String size;
    private String filePath;
    private String uploadedDate;
    private String modifiedDate;
    private String description;
}