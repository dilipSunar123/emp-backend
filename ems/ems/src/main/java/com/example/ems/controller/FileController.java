package com.example.ems.controller;

import com.example.ems.entity.UploadedFile;
import com.example.ems.repository.UploadedFileRepository;
import com.example.ems.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class FileController {

    private final FileService fileService;

    @Autowired
    UploadedFileRepository uploadedFileRepository;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") String filePath, @RequestParam int emp_id, @RequestParam int fileTypeId) {
        try {
            fileService.saveFile(filePath, emp_id, fileTypeId);

            return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully");
        } catch (IOException e) {
            System.out.println("Error : " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
        }
    }

    @GetMapping("/file/{id}")
    public ResponseEntity<byte[]> getFileById(@PathVariable int id) {
        UploadedFile uploadedFile = fileService.getFileById(id);

//        if (uploadedFile != null) {
//            return ResponseEntity.ok()
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + uploadedFile.getFileName() + "\"")
//                    .body(uploadedFile.getData());
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        }

        if (uploadedFile != null) {
            byte[] fileData = uploadedFile.getData();

            if (fileData != null) {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_PDF); // Change to appropriate type for PDFs or other file formats

                return ResponseEntity.ok()
                        .headers(headers)
                        .body(fileData);
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

//    @GetMapping("findFileByCategory/{empId}/{fileTypeId}")
//    public List<UploadedFile> findFileByCategory(@PathVariable int empId, @PathVariable int fileTypeId) {
//        return uploadedFileRepository.findByEmployeeEntityEmpIdAndFileTypeEntityFileTypeId(empId, fileTypeId);
//    }

}
