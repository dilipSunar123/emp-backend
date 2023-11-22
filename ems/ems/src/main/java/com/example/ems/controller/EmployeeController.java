package com.example.ems.controller;

import com.example.ems.entity.EmployeeEntity;
import com.example.ems.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    public EmployeeRepo registerRepo;


    // return all the employees in the org.
    @GetMapping("/getAllEmployees")
    private List<EmployeeEntity> getAllEmployees() {
        return registerRepo.findAll();
    }


    // register a new employee
    @PostMapping("/addEmployee")
    private ResponseEntity addEmployee(@RequestBody EmployeeEntity entity) {

        registerRepo.save(entity);

        return ResponseEntity.ok("Employee added");

    }

//    @GetMapping("/findEmployeeById/{id}")
//    private Optional<EmployeeEntity> findEmployee(@RequestParam int emp_id) {
//        return registerRepo.findById(emp_id);
//    }

    // login
    @GetMapping("/findEmployee/{email}/{password}")
    private boolean employeeExists(@PathVariable String email, @PathVariable String password) {

        boolean emailExists = false;
        EmployeeEntity myLocalEmployeeEntity = new EmployeeEntity();

        List<EmployeeEntity> employeeEntityList = registerRepo.findAll();

        for(EmployeeEntity singleEntity: employeeEntityList) {
            if (singleEntity.getEmail().equals(email)) {
                emailExists = true;

                myLocalEmployeeEntity = singleEntity;
            }
        }

        return emailExists && myLocalEmployeeEntity.getPassword().equals(password);

    }

}
