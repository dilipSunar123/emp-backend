package com.example.ems.service;

import com.example.ems.entity.EmployeeEntity;
import com.example.ems.entity.FileTypeEntity;
import com.example.ems.entity.UploadedFile;
import com.example.ems.repository.EmployeeRepo;
import com.example.ems.repository.FileTypeRepository;
import com.example.ems.repository.UploadedFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {

    private final UploadedFileRepository fileRepository;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private FileTypeRepository fileTypeRepository;


    @Autowired
    public FileService(UploadedFileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public void saveFile(String filePath, int emp_id, int fileTypeId) throws IOException {
        UploadedFile uploadedFile = new UploadedFile();
        EmployeeEntity employeeEntity = employeeRepo.getReferenceById(emp_id);
        FileTypeEntity fileTypeEntity = fileTypeRepository.getReferenceById(fileTypeId);


        // Read file content as bytes
        Path path = Paths.get(filePath);
        byte[] fileContent = Files.readAllBytes(path);

        uploadedFile.setFileName(filePath);
        uploadedFile.setData(fileContent);
        uploadedFile.setEmployeeEntity(employeeEntity);
        uploadedFile.setFileTypeEntity(fileTypeEntity);

        fileRepository.save(uploadedFile);
    }

    public UploadedFile getFileById(int id) {
//        UploadedFile uploadedFile = fileRepository.findById(id).orElse(null);
//
//        if (uploadedFile != null) {
//            return uploadedFile.getData();
//        }

        return fileRepository.findById(id).orElse(null);
    }

}
