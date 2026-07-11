package com.solidsudogear.ccsp.Model;

import lombok.Data;

@Data
public class FileInfo {

    private String fileName;
    private String contentType;
    private Long FileLength;
    private Boolean readable;
    private Boolean isFileEmpty;
    private byte[] fileData;

}



/*
string name
long length
Sting contentType
boolean readAble
boolean isEmpty
Byte[] fileData
*/
