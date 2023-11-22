package com.example.ems.entity;

import jakarta.persistence.*;

// register
@Entity
@Table(name = "employee")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int emp_id;
    @Column(nullable = false)
    private String emp_name;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String skills;
    @Column(nullable = false)
    private Long contact_no;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String job_role;

    private String flag = "active";
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dept_id")
    private DepartmentEntity departmentEntity;


    public int getEmp_id() {
        return emp_id;
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

    public String getJob_role() {
        return job_role;
    }

    public String getFlag() {
        return flag;
    }

    public DepartmentEntity getDepartmentEntity() {
        return departmentEntity;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
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

    public void setJob_role(String job_role) {
        this.job_role = job_role;
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
                "emp_id=" + emp_id +
                ", emp_name='" + emp_name + '\'' +
                ", email='" + email + '\'' +
                ", skills='" + skills + '\'' +
                ", contact_no=" + contact_no +
                ", password='" + password + '\'' +
                ", job_role='" + job_role + '\'' +
                ", flag='" + flag + '\'' +
                ", departmentEntity=" + departmentEntity +
                '}';
    }
}
