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
import java.util.Optional;

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

    public void saveFile(String file, int emp_id, int fileTypeId) throws IOException {
        UploadedFile uploadedFile = new UploadedFile();
        EmployeeEntity employeeEntity = employeeRepo.getReferenceById(emp_id);
        FileTypeEntity fileTypeEntity = fileTypeRepository.getReferenceById(fileTypeId);

        uploadedFile.setFileName(file);
        uploadedFile.setData(file.getBytes());
        uploadedFile.setEmployeeEntity(employeeEntity);
        uploadedFile.setFileTypeEntity(fileTypeEntity);

        fileRepository.save(uploadedFile);
    }

    public UploadedFile getFileById(int id) {
        System.out.println("file : " + fileRepository.findById(id));

        return fileRepository.findById(id).orElse(null);
    }

}
