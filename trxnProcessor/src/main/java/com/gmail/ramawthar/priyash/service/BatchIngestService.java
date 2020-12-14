package com.gmail.ramawthar.priyash.service;

import org.springframework.web.multipart.MultipartFile;

public interface BatchIngestService {
    String processCSVFile(MultipartFile file);
}
