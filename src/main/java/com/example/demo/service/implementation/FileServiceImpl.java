package com.example.demo.service.implementation;

import com.example.demo.service.FileService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public File multipartFileToFile(MultipartFile multipartFile, String fileName) throws IOException {
        File file = new File("src/main/resources/" + fileName);
        try (OutputStream os = new FileOutputStream(file)) {
            os.write(multipartFile.getBytes());
        }
        return file;
    }

    @Override
    public String generateFileName(String fileExtension) {
        String fileName = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), fileExtension);
        return new Date().getTime() + "-" + fileName;
    }

    @Override
    public boolean deleteFile(File file) {
        return file.delete();
    }

    @Override
    public void checkMultipartFileExtension(String fileName, List<String> extensions) throws IOException {
        String fileExtension = FilenameUtils.getExtension(fileName);
        if(!extensions.contains(fileExtension)){
            throw new IOException("Invalid file extension");
        }
    }
}
