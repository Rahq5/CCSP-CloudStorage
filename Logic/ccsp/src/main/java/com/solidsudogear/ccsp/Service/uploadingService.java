package com.solidsudogear.ccsp.Service;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface uploadingService {

    String uploading (MultipartFile file);
}
