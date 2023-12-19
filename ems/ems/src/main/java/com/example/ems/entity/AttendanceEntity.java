package com.example.ems.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Table(name = "attendance")
public class AttendanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int slno;

    @Column(name = "login_date_and_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime loginDateAndTime;

    @Column(name = "logout_date_and_time")
    private LocalDateTime logout_date_and_time;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "emp_id", nullable = false)
    private EmployeeEntity employeeEntity;

    public int getSlno() {
        return slno;
    }

    public LocalDateTime getLoginDateAndTime() {
        return loginDateAndTime;
    }

    public LocalDateTime getLogout_date_and_time() {
        return logout_date_and_time;
    }

    public EmployeeEntity getEmployeeEntity() {
        return employeeEntity;
    }

    public void setSlno(int slno) {
        this.slno = slno;
    }

    public void setLoginDateAndTime(LocalDateTime loginDateAndTime) {
        this.loginDateAndTime = loginDateAndTime;
    }

    public void setLogout_date_and_time(LocalDateTime logout_date_and_time) {
        this.logout_date_and_time = logout_date_and_time;
    }

    public void setEmployeeEntity(EmployeeEntity employeeEntity) {
        this.employeeEntity = employeeEntity;
    }
}
