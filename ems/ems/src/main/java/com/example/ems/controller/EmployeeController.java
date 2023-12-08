package com.example.ems.controller;

import com.example.ems.entity.EmployeeEntity;
import com.example.ems.entity.JobRoleEntity;
import com.example.ems.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    public EmployeeRepo registerRepo;


    // return all the employees in the org.
    @GetMapping("/getAllEmployees")
    private List<EmployeeEntity> getAllEmployees() {
        return registerRepo.findAll();
    }


//    @GetMapping("/getAllEmployees")
//    private List<EmployeeDto> getAllEmployees() {
//        List<EmployeeEntity> employeeEntityList = registerRepo.findAll();
//
//        return employeeEntityList.stream().map(EmployeeDto::new).collect(Collectors.toList());
//    }


    // register a new employee
    @PostMapping("/addEmployee")
    private ResponseEntity addEmployee(@RequestBody EmployeeEntity entity) {

        System.out.println(entity);

        registerRepo.save(entity);

        return ResponseEntity.ok("Employee added");
    }

//    @GetMapping("/findEmployeeByJobId/{job_id}")
//    private List<JobRoleEntity> findEmployee(@RequestParam int job_id) {
//        return registerRepo.findByJobRoleEntityJobId(job_id);
//    }

    // login
//    @GetMapping("/findEmployee/{email}/{password}")
//    private boolean employeeExists(@PathVariable String email, @PathVariable String password) {
//
//        boolean emailExists = false;
//        EmployeeEntity myLocalEmployeeEntity = new EmployeeEntity();
//
//        List<EmployeeEntity> employeeEntityList = registerRepo.findAll();
//
//        for(EmployeeEntity singleEntity: employeeEntityList) {
//            if (singleEntity.getEmail().equals(email)) {
//                emailExists = true;
//
//                myLocalEmployeeEntity = singleEntity;
//            }
//        }
//
//        return emailExists && myLocalEmployeeEntity.getPassword().equals(password);
//
//    }


    // login
    @GetMapping("/findEmployee/{email}/{password}")
    private int employeeExists(@PathVariable String email, @PathVariable String password) {

        boolean emailExists = false;
        EmployeeEntity myLocalEmployeeEntity = new EmployeeEntity();

        List<EmployeeEntity> employeeEntityList = registerRepo.findAll();

        for (EmployeeEntity singleEntity : employeeEntityList) {
            if (singleEntity.getEmail().equals(email)) {
                emailExists = true;
                myLocalEmployeeEntity = singleEntity;

                break;
            }
        }

        if (emailExists && myLocalEmployeeEntity.getPassword().equals(password)) {
            return myLocalEmployeeEntity.getJobRoleEntity().getJob_id();
        }

        return -1;
    }
}
