package com.example.demo.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FileService {
    File multipartFileToFile(MultipartFile multipartFile, String fileName) throws IOException;
    String generateFileName(String fileExtension);
    boolean deleteFile(File file);
    void checkMultipartFileExtension(String fileName, List<String> extensions) throws IOException;
}
