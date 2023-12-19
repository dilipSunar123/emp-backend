package com.example.ems.repository;

import com.example.ems.entity.EmployeeEntity;
import com.example.ems.entity.JobRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepo extends JpaRepository<EmployeeEntity, Integer> {

//    public List<JobRoleEntity> findByJobRoleEntityJobId(int job_id);


}
