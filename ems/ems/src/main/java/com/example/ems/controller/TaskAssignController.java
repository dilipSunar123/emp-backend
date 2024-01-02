package com.example.ems.controller;

import com.example.ems.entity.TaskAssignEntity;
import com.example.ems.repository.TaskAssignRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class TaskAssignController {

    @Autowired
    private TaskAssignRepo taskAssignRepo;

    @GetMapping("/findByEmployeeEntityEmpId/{empId}")
    private List<TaskAssignEntity> findByEmployeeEntityEmpId (@PathVariable int empId) {
        return taskAssignRepo.findByEmployeeEntityEmpId(empId);
    }

    @PostMapping("/addTask")
    private ResponseEntity<?> addTask (@RequestBody TaskAssignEntity entity) {

        TaskAssignEntity myEntity = new TaskAssignEntity();

        myEntity.setTaskTitle(entity.getTaskTitle());
        myEntity.setTaskDesc(entity.getTaskDesc());
        myEntity.setStatus(entity.getStatus());
        myEntity.setEmployeeEntity(entity.getEmployeeEntity());

        LocalDateTime localDateTime = LocalDateTime.now();
        myEntity.setStartDate(localDateTime);   //
        myEntity.setEndDate(localDateTime); //

        taskAssignRepo.save(myEntity);

        return ResponseEntity.ok("New Task Assigned");
    }

    @PutMapping("changeStatus/{taskId}/{status}")
    private ResponseEntity<?> changeStatus (@PathVariable int taskId, @PathVariable String status) {

        TaskAssignEntity entity = taskAssignRepo.getReferenceById(taskId);

        entity.setStatus(status);   // updated one
        entity.setEmployeeEntity(entity.getEmployeeEntity());
        entity.setTaskTitle(entity.getTaskTitle());
        entity.setTaskDesc(entity.getTaskDesc());
        entity.setStartDate(entity.getStartDate());
        entity.setEndDate(entity.getEndDate());
        entity.setSlno(entity.getSlno());

        taskAssignRepo.save(entity);

        return ResponseEntity.ok("Status Changed");

    }

}
