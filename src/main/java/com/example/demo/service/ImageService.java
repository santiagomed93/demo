package com.example.demo.service;

import java.io.File;

public interface ImageService {
    String createImage(File file);
    void deletePhoto(String fileName);
}
