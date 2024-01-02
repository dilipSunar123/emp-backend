package com.example.ems.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "leave")
public class LeaveEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int slno;

    @Column(name = "start_date")
    private String startDate;    // start_date needs to be stored as String as JSON do not support LocalDateTime as a datatype

    @Column(name = "end_date")
    private String endDate;

    @Column(name = "status")
    private String status;

    @Column(name = "reason")
    private String reason;

    @ManyToOne
    @JoinColumn(name = "empId")
    private EmployeeEntity employeeEntity;

    public int getSlno() {
        return slno;
    }

    public void setSlno(int slno) {
        this.slno = slno;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public EmployeeEntity getEmployeeEntity() {
        return employeeEntity;
    }

    public void setEmployeeEntity(EmployeeEntity employeeEntity) {
        this.employeeEntity = employeeEntity;
    }

    @Override
    public String toString() {
        return "LeaveEntity{" +
                "slno=" + slno +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", status='" + status + '\'' +
                ", reason='" + reason + '\'' +
                ", employeeEntity=" + employeeEntity +
                '}';
    }
}
