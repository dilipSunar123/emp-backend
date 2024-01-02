package com.example.ems.repository;

import com.example.ems.entity.UploadedFile;
import com.example.ems.service.FileService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UploadedFileRepository extends JpaRepository<UploadedFile, Integer> {

    List<UploadedFile> findByEmployeeEntityEmpIdAndFileTypeEntityFileTypeId(int empId, int fileTypeId);

}