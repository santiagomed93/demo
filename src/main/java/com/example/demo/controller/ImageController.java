package com.example.demo.controller;

import com.example.demo.service.FileService;
import com.example.demo.service.ImageService;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@RestController
@AllArgsConstructor
public class ImageController {

    private static List<String> ALLOW_EXTENSIONS = Arrays.asList("jpg", "jpeg", "png");
    private ImageService imageService;
    private FileService fileService;

    @PostMapping("/images")
    public ResponseEntity<Long> uploadPhoto(@RequestParam("imageFile")MultipartFile multipartFile) throws Exception {
        fileService.checkMultipartFileExtension(multipartFile.getOriginalFilename(),ALLOW_EXTENSIONS);
        File file = fileService.multipartFileToFile(multipartFile, fileService.generateFileName(FilenameUtils.getExtension(multipartFile.getOriginalFilename())));
        imageService.createImage(file);
        fileService.deleteFile(file);
        return new ResponseEntity<>(123L, HttpStatus.OK);
    }
}
