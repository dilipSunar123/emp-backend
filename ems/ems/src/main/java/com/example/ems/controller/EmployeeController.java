package com.example.ems.controller;

import com.example.ems.entity.EmployeeEntity;
import com.example.ems.entity.EmployeeTypeEntity;
import com.example.ems.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    public EmployeeRepo registerRepo;
    private final JavaMailSender javaMailSender;

    @Autowired
    public EmployeeController(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


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
    private ResponseEntity<?> addEmployee(@RequestBody EmployeeEntity entity) {
        // when a user is registered, he/she will be a normal employee
        EmployeeTypeEntity employeeType = new EmployeeTypeEntity();
        employeeType.setemployeeTypeId(1);

        entity.setEmployeeType(employeeType);

        registerRepo.save(entity);

        sendEmail(entity.getEmail(), entity);

        return ResponseEntity.ok("Employee added");
    }

    public String sendEmail(String emailAdd, EmployeeEntity entity) {
        String password = entity.getPassword();

        System.out.println(password);

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(emailAdd);
        message.setSubject("Login Credentials");
        message.setText("Congratulations!\nYou have been registered successfully.\n\nYour login credentials are:\n\nusername: " + emailAdd + "\npassword: " + password + "\n\nClick on the link below to login  \n https://www.pexels.com/");

        javaMailSender.send(message);

        return "Email sent successfully!";
    }


    // login
    @GetMapping("/findEmployee/{email}/{password}")
    @CrossOrigin
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

    @GetMapping("/checkEmailExists/{email}")
    boolean checkEmailExists (@PathVariable String email) {
        List<EmployeeEntity> employeeEntityList = registerRepo.findAll();

        for (EmployeeEntity employeeEntity: employeeEntityList) {

            if (employeeEntity.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    @PutMapping("/resetPassword/{email}/{password}")
    void addPassword(@PathVariable String email, @PathVariable String password) {

        int idOfCorrespondingEmail = 0;

        List<EmployeeEntity> employeeEntityList = registerRepo.findAll();

        for (EmployeeEntity employeeEntity: employeeEntityList) {
            if (employeeEntity.getEmail().equals(email)) {
                idOfCorrespondingEmail = employeeEntity.getEmpId();
            }
        }

        EmployeeEntity employeeEntity = registerRepo.getReferenceById(idOfCorrespondingEmail);

        employeeEntity.setPassword(password);   // updated one
        employeeEntity.setEmpId(employeeEntity.getEmpId());
        employeeEntity.setEmp_name(employeeEntity.getEmp_name());
        employeeEntity.setEmail(employeeEntity.getEmail());
        employeeEntity.setSkills(employeeEntity.getSkills());
        employeeEntity.setContact_no(employeeEntity.getContact_no());
        employeeEntity.setContact_no(employeeEntity.getContact_no());
        employeeEntity.setPermanent_address(employeeEntity.getPermanent_address());
        employeeEntity.setCorrespondence_address(employeeEntity.getCorrespondence_address());
        employeeEntity.setJobRoleEntity(employeeEntity.getJobRoleEntity());
        employeeEntity.setFlag(employeeEntity.getFlag());
        employeeEntity.setDepartmentEntity(employeeEntity.getDepartmentEntity());
        employeeEntity.setReportingManagerId(employeeEntity.getReportingManagerId());

        registerRepo.save(employeeEntity);
    }

    @GetMapping("/findAllEmployeeIdAndName")
    private HashMap<String, Integer> findAllEmployeeIdAndName() {

        HashMap<String, Integer> employeeList = new HashMap<>();

        List<EmployeeEntity> list = registerRepo.findAll();

        for (EmployeeEntity entity: list) {
            String empName = entity.getEmp_name();
            int empId = entity.getEmpId();

            employeeList.put(empName, empId);
        }

        return employeeList;

    }

    @PutMapping("/addReportingManager/{email}/{managerId}")
    private ResponseEntity addReportingManager(@PathVariable String email, @PathVariable int managerId) {

        int idOfCorrespondingEmail = 0;

        List<EmployeeEntity> employeeEntityList = registerRepo.findAll();

        for (EmployeeEntity entity: employeeEntityList) {
            if (entity.getEmail().equals(email)) {
                idOfCorrespondingEmail = entity.getEmpId();
            }
        }

        // check if the managerId provided is genuinely a manager
        EmployeeEntity employee = registerRepo.getReferenceById(managerId);

        if (employee.getEmployeeType().getemployeeTypeId() == 2) {

            EmployeeEntity employeeEntity = registerRepo.getReferenceById(idOfCorrespondingEmail);

            employeeEntity.setPassword(employeeEntity.getPassword());
            employeeEntity.setEmpId(employeeEntity.getEmpId());
            employeeEntity.setEmp_name(employeeEntity.getEmp_name());
            employeeEntity.setEmail(employeeEntity.getEmail());
            employeeEntity.setSkills(employeeEntity.getSkills());
            employeeEntity.setContact_no(employeeEntity.getContact_no());
            employeeEntity.setContact_no(employeeEntity.getContact_no());
            employeeEntity.setPermanent_address(employeeEntity.getPermanent_address());
            employeeEntity.setCorrespondence_address(employeeEntity.getCorrespondence_address());
            employeeEntity.setJobRoleEntity(employeeEntity.getJobRoleEntity());
            employeeEntity.setFlag(employeeEntity.getFlag());
            employeeEntity.setDepartmentEntity(employeeEntity.getDepartmentEntity());
            employeeEntity.setReportingManagerId(managerId);  // updated one

            registerRepo.save(employeeEntity);

            return ResponseEntity.ok("Manager ID added");

        }
        System.out.println("Nahi");
        return null;

    }

    @GetMapping("/getEmployeesByManagerId/{managerId}")
    private List<EmployeeEntity> getEmployeesByManagerId(@PathVariable int managerId) {
        return registerRepo.findByReportingManagerId(managerId);
    }


    // find all managers or employee by id
    @GetMapping("/findByEmployeeTypeEmployeeTypeId/{managerId}")
    private List<EmployeeEntity> findEmployeesByManagerId(@PathVariable int managerId) {
        return registerRepo.findByEmployeeTypeEmployeeTypeId(managerId);
    }


    @GetMapping("/getEmployeesByDepartmentId/{deptId}")
    private List<?> getEmployeesByDepartmentId(@PathVariable int deptId) {
        return registerRepo.findByDepartmentEntityDeptId(deptId);
    }

    @PutMapping("/setEmployeeAsManager/{empId}")
    private void setEmployeeAsManager(@PathVariable int empId) {

        EmployeeEntity entity = registerRepo.getReferenceById(empId);

        // checking if the person is already not a manager
        if (entity.getEmployeeType().getemployeeTypeId() != 2) {

            entity.setPassword(entity.getPassword());
            entity.setEmpId(entity.getEmpId());
            entity.setEmp_name(entity.getEmp_name());
            entity.setEmail(entity.getEmail());
            entity.setSkills(entity.getSkills());
            entity.setContact_no(entity.getContact_no());
            entity.setContact_no(entity.getContact_no());
            entity.setPermanent_address(entity.getPermanent_address());
            entity.setCorrespondence_address(entity.getCorrespondence_address());
            entity.setJobRoleEntity(entity.getJobRoleEntity());
            entity.setFlag(entity.getFlag());
            entity.setDepartmentEntity(entity.getDepartmentEntity());
            entity.setReportingManagerId(entity.getReportingManagerId());

            // updation
            EmployeeTypeEntity employeeType = new EmployeeTypeEntity();
            employeeType.setemployeeTypeId(2);

            entity.setEmployeeType(employeeType);


            registerRepo.save(entity);
        }

    }
}
