package com.example.ems.entity;

import jakarta.persistence.*;

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

//    @Column(nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "job_id")
    private JobRoleEntity jobRoleEntity;

    private String flag = "active";

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dept_id", nullable = true)
    private DepartmentEntity departmentEntity;


    public int getEmp_id() {
        return empId;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public String getEmail() {
        return email;
    }

    public String getSkills() {
        return skills;
    }

    public Long getContact_no() {
        return contact_no;
    }

    public String getPassword() {
        return password;
    }

    public JobRoleEntity getJobRoleEntity() {
        return jobRoleEntity;
    }

    public String getFlag() {
        return flag;
    }

    public DepartmentEntity getDepartmentEntity() {
        return departmentEntity;
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

    public void setEmp_id(int emp_id) {
        this.empId = emp_id;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public void setContact_no(Long contact_no) {
        this.contact_no = contact_no;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setJobRoleEntity(JobRoleEntity jobRoleEntity) {
        this.jobRoleEntity = jobRoleEntity;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public void setDepartmentEntity(DepartmentEntity departmentEntity) {
        this.departmentEntity = departmentEntity;
    }

    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "emp_id=" + empId +
                ", emp_name='" + emp_name + '\'' +
                ", email='" + email + '\'' +
                ", skills='" + skills + '\'' +
                ", contact_no=" + contact_no +
                ", password='" + password + '\'' +
                ", jobRoleEntity=" + jobRoleEntity +
                ", flag='" + flag + '\'' +
                ", departmentEntity=" + departmentEntity +
                '}';
    }
}
