package com.example.ems.service;

import com.example.ems.entity.UploadedFile;
import com.example.ems.repository.UploadedFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FileService {

    private final UploadedFileRepository fileRepository;

    @Autowired
    public FileService(UploadedFileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public void saveFile(String file) throws IOException {
        UploadedFile uploadedFile = new UploadedFile();
        uploadedFile.setFileName("File Name");
        uploadedFile.setData(file.getBytes());

        fileRepository.save(uploadedFile);
    }

    public UploadedFile getFileById(int id) {
        return fileRepository.findById(id).orElse(null);
    }

}
