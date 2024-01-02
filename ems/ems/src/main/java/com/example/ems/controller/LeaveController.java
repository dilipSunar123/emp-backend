package com.example.ems.controller;

import com.example.ems.entity.EmployeeEntity;
import com.example.ems.entity.LeaveEntity;
import com.example.ems.repository.EmployeeRepo;
import com.example.ems.repository.LeaveRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class LeaveController {

    @Autowired
    private LeaveRepo repo;

    @Autowired
    private EmployeeRepo employeeRepo;

    private JavaMailSender javaMailSender;

    @Autowired
    public LeaveController(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @PostMapping("/requestLeave")
    private ResponseEntity<?> addLeave(@RequestBody LeaveEntity entity) {

        LeaveEntity leaveEntity = new LeaveEntity();

        leaveEntity.setStartDate(entity.getStartDate());
        leaveEntity.setEndDate(entity.getEndDate());
        leaveEntity.setEmployeeEntity(entity.getEmployeeEntity());
        leaveEntity.setReason(entity.getReason());
        leaveEntity.setStatus("Pending");   // not yet approved

//        int managerId = entity.getEmployeeEntity().getReportingManagerId();

        int empId = entity.getEmployeeEntity().getEmpId();

        EmployeeEntity empEntity = employeeRepo.getReferenceById(empId);

        int managerId = empEntity.getReportingManagerId();

        String msg = sendEmailToManager(managerId, entity.getEmployeeEntity().getEmpId());

        if (msg.equals("No reporting manager found for employee")) {
            return ResponseEntity.ok("No reporting manager found for employee");
        }

        repo.save(leaveEntity);

        return ResponseEntity.ok("Leave request sent for approval");

    }

    private String sendEmailToManager(@PathVariable int managerId, @PathVariable int empId) {

        SimpleMailMessage message = new SimpleMailMessage();

        // manager Email
        EmployeeEntity manager = employeeRepo.getReferenceById(managerId);
        EmployeeEntity employee = employeeRepo.getReferenceById(empId);

        if (employee.getReportingManagerId() == 0)
            return "No reporting manager found for employee";

        message.setTo(manager.getEmail());
        message.setSubject("Leave Approval");
        message.setText("Hello, " +
                manager.getEmp_name() + "!\n\nMr/Mrs " + employee.getEmp_name() +
                " is requesting for a leave. \nClick on the link below to review the leave request and take appropriate " +
                "action on it.");

        javaMailSender.send(message);

        return "Email sent to the manager";
    }

    @GetMapping("findLeave/{leaveId}")
    private Optional<LeaveEntity> findLeaveRequest(@PathVariable int leaveId) {
        return repo.findById(leaveId);
    }

    @PutMapping("/takeActionOnLeaveRequest/{leaveId}/{action}")
    private ResponseEntity<?> takeActionOnLeaveRequest(@PathVariable int leaveId, @PathVariable String action) {

        LeaveEntity entity = repo.getReferenceById(leaveId);

        LeaveEntity myLocalLeaveEntity = new LeaveEntity();

        myLocalLeaveEntity.setSlno(entity.getSlno());
        myLocalLeaveEntity.setReason(entity.getReason());
        myLocalLeaveEntity.setEmployeeEntity(entity.getEmployeeEntity());
        myLocalLeaveEntity.setStartDate(entity.getStartDate());
        myLocalLeaveEntity.setEndDate(entity.getEndDate());
        myLocalLeaveEntity.setStatus(action);   // updated one

        repo.save(myLocalLeaveEntity);

        if (action.equals("Approve"))
            return ResponseEntity.ok("Leave Approved");

        return ResponseEntity.ok("Leave request rejected");
    }

}
