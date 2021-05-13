package com.example.demo.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@RequiredArgsConstructor
public class AmazonClient {

    private final AmazonS3 s3client;
    private String bucketName = "test-santiago";

    public String uploadFile(File file) {
        String fileName = FilenameUtils.getBaseName(file.getName());
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName,fileName, file);
        s3client.putObject(putObjectRequest);
        return fileName;
    }

    public void deleteImageFromS3Bucket(String fileName) {
        s3client.deleteObject(bucketName, fileName);
    }

    public void deleteThumbnailFromS3Bucket(String fileName) {
        s3client.deleteObject(bucketName + "-resized", fileName);
    }
}
