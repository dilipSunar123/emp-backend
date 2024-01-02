package com.example.ems.entity;

import jakarta.persistence.*;

import java.util.Random;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// register
@Entity
@Table(name = "employee")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int empId;
    @Column(nullable = false)
    private String emp_name;
    @Column(unique = true, nullable = false)
    private String email;
    private String skills;
    @Column(nullable = false)
    private Long contact_no;

    @Column(nullable = true)
    private Long alternate_contact_no;

    @Column(nullable = false)
    private String permanent_address;


    @Column(nullable = false)
    private String correspondence_address;

    private String password = generateRandomPassword();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "job_id")
    private JobRoleEntity jobRoleEntity;

    private String flag = "active";

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "deptId", nullable = true)
    private DepartmentEntity departmentEntity;

    @Column(name = "reporting_manager_id")
    private int reportingManagerId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employeeTypeId")
    private EmployeeTypeEntity employeeType;


    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public Long getContact_no() {
        return contact_no;
    }

    public void setContact_no(Long contact_no) {
        this.contact_no = contact_no;
    }

    public Long getAlternate_contact_no() {
        return alternate_contact_no;
    }

    public void setAlternate_contact_no(Long alternate_contact_no) {
        this.alternate_contact_no = alternate_contact_no;
    }

    public String getPermanent_address() {
        return permanent_address;
    }

    public void setPermanent_address(String permanent_address) {
        this.permanent_address = permanent_address;
    }

    public String getCorrespondence_address() {
        return correspondence_address;
    }

    public void setCorrespondence_address(String correspondence_address) {
        this.correspondence_address = correspondence_address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public JobRoleEntity getJobRoleEntity() {
        return jobRoleEntity;
    }

    public void setJobRoleEntity(JobRoleEntity jobRoleEntity) {
        this.jobRoleEntity = jobRoleEntity;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public DepartmentEntity getDepartmentEntity() {
        return departmentEntity;
    }

    public void setDepartmentEntity(DepartmentEntity departmentEntity) {
        this.departmentEntity = departmentEntity;
    }

    public int getReportingManagerId() {
        return reportingManagerId;
    }

    public void setReportingManagerId(int reportingManagerId) {
        this.reportingManagerId = reportingManagerId;
    }

    public EmployeeTypeEntity getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeTypeEntity employeeType) {
        this.employeeType = employeeType;
    }

    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "empId=" + empId +
                ", emp_name='" + emp_name + '\'' +
                ", email='" + email + '\'' +
                ", skills='" + skills + '\'' +
                ", contact_no=" + contact_no +
                ", alternate_contact_no=" + alternate_contact_no +
                ", permanent_address='" + permanent_address + '\'' +
                ", correspondence_address='" + correspondence_address + '\'' +
                ", password='" + password + '\'' +
                ", jobRoleEntity=" + jobRoleEntity +
                ", flag='" + flag + '\'' +
                ", departmentEntity=" + departmentEntity +
                ", reportingManagerId=" + reportingManagerId +
                ", employeeType=" + employeeType +
                '}';
    }

    public String generateRandomPassword() {
        Random random = new Random();

        int randomNumber = 100000 + random.nextInt(900000);

        return "ems" + String.valueOf(randomNumber);
    }
}
