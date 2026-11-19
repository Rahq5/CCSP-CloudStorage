package com.solidsudogear.ccsp.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.solidsudogear.ccsp.DTOs.FileDto;
import com.solidsudogear.ccsp.DTOs.FileResponseDTO;

@Service 
public interface getAllFiles {

    List<FileResponseDTO> getFiles(); 
} 
