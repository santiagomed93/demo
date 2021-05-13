package com.example.demo.service.implementation;

import com.example.demo.service.AmazonClient;
import com.example.demo.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@AllArgsConstructor
public class ImageServiceImpl implements ImageService {

    private AmazonClient amazonClient;

    @Override
    public String createImage(File file) {
        return amazonClient.uploadFile(file);
    }

    @Override
    public void deletePhoto(String fileName) {
        amazonClient.deleteImageFromS3Bucket(fileName);
        amazonClient.deleteThumbnailFromS3Bucket(fileName);
    }
}
