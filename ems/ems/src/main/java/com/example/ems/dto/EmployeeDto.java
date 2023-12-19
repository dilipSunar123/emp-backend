//package com.example.ems.dto;
//
//import com.example.ems.entity.EmployeeEntity;
//import com.example.ems.repository.EmployeeRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//
//public class EmployeeDto {
//
//    @Autowired
//    private EmployeeRepo employeeRepo;
//
//    private int emp_id;
//    private String emp_name;
//    private String email;
//    private String skills;
//    private String contact_no;
//    private int job_id;
//    private final String flag = "active";
//    private int dept_id;
//
//    public EmployeeDto(EmployeeEntity employeeEntity) {
//        this.emp_id = employeeEntity.getEmp_id();
//        this.emp_name = employeeEntity.getEmp_name();
//        this.email = employeeEntity.getEmail();
//        this.skills = employeeEntity.getSkills();
//        this.contact_no = employeeEntity.getContact_no();
//
//        int job_id = employeeRepo.getReferenceById(employeeEntity.getEmp_id()).getEmp_id();
//        this.job_id = job_id;
//
//        int dept_id = employeeRepo.getReferenceById(employeeEntity.getDepartmentEntity().getDept_id()).getDepartmentEntity().getDept_id();
//
//        this.dept_id = dept_id;
//    }
//
//    public int getEmp_id() {
//        return emp_id;
//    }
//
//    public String getEmp_name() {
//        return emp_name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public String getSkills() {
//        return skills;
//    }
//
//    public String getContact_no() {
//        return contact_no;
//    }
//
//    public int getJob_id() {
//        return job_id;
//    }
//
//    public String getFlag() {
//        return flag;
//    }
//
//    public int getDept_id() {
//        return dept_id;
//    }
//
//    public void setEmp_id(int emp_id) {
//        this.emp_id = emp_id;
//    }
//
//    public void setEmp_name(String emp_name) {
//        this.emp_name = emp_name;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public void setSkills(String skills) {
//        this.skills = skills;
//    }
//
//    public void setContact_no(String contact_no) {
//        this.contact_no = contact_no;
//    }
//
//    public void setJob_id(int job_id) {
//        this.job_id = job_id;
//    }
//
//    public void setDept_id(int dept_id) {
//        this.dept_id = dept_id;
//    }
//}
