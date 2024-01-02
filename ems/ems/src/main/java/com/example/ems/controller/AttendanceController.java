package com.example.ems.controller;


import com.example.ems.entity.AttendanceEntity;
import com.example.ems.entity.EmployeeEntity;
import com.example.ems.repository.AttendanceRepo;
import com.example.ems.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class AttendanceController {

    @Autowired
    AttendanceRepo attendanceRepo;

    @Autowired
    EmployeeRepo employeeRepo;

    @GetMapping("/getAllAttendance")
    private List<AttendanceEntity> getAllAttendance () {
        return attendanceRepo.findAll();
    }

    @PostMapping("/addAttendance/{emp_id}")
    private ResponseEntity addAttendance(@PathVariable int emp_id) {
        LocalDateTime localDateTime = LocalDateTime.now();

        AttendanceEntity entity = new AttendanceEntity();

        EmployeeEntity employeeEntity = employeeRepo.getReferenceById(emp_id);
        entity.setEmployeeEntity(employeeEntity);
        entity.setLoginDateAndTime(localDateTime);

        // Create a LocalTime representing 9:30 AM
//        LocalTime comparisonTime = LocalTime.of(9, 30);
//
//        Map<Boolean, Boolean> lateOrOnTimeMap = new HashMap<>();
//
//        if (localDateTime.toLocalTime().isAfter(comparisonTime)) {
//            lateOrOnTimeMap = addLateOrOnTime(emp_id, true);
//        } else {
//            lateOrOnTimeMap = addLateOrOnTime(emp_id, false);
//        }
//
//        // employee on time
//        if (lateOrOnTimeMap.containsKey(true)) {
//            entity.setOnTime(true);
//            entity.setLateArrival(false);
//        } else {
//            entity.setOnTime(false);
//            entity.setLateArrival(true);
//        }

        attendanceRepo.save(entity);

//        lateOrOnTimeMap.clear();

        return ResponseEntity.ok("Attendance recorded");
    }

//    private Map<Boolean, Boolean> addLateOrOnTime(int empId, boolean isLate) {
//        List<AttendanceEntity> entity = attendanceRepo.findByEmployeeEntityEmpIdOrderByLoginDateAndTimeDesc(empId);
//
//        // key -> onTime && value -> lateArrival
//        Map<Boolean, Boolean> lateOrOnTimeMap = new HashMap<>();
//
//        for (AttendanceEntity attendanceEntity: entity) {
//            if (attendanceEntity.getEmployeeEntity().getEmp_id() == empId) {
//                if (isLate) {
////                    attendanceEntity.setLateArrival(true);
////                    attendanceEntity.setOnTime(false);
//
//                    lateOrOnTimeMap.put(false, true);
//                } else {
////                    attendanceEntity.setLateArrival(false);
////                    attendanceEntity.setOnTime(true);
//
//                    lateOrOnTimeMap.put(true, false);
//                }
//                break;
//            }
//        }
//        return lateOrOnTimeMap;
//    }


    @GetMapping("/getAttendanceOfEmp/{empId}")
    public List<AttendanceEntity> getAttendanceOfEmp(@PathVariable int empId) {
        return attendanceRepo.findByEmployeeEntityEmpId(empId);
    }

    @PutMapping("/addLogoutDateAndTime/{emp_id}")
    private ResponseEntity addLogoutDateAndTime(@PathVariable int emp_id) {
        List<AttendanceEntity> attendanceEntity = attendanceRepo.findByEmployeeEntityEmpIdOrderByLoginDateAndTimeDesc(emp_id);

        for (AttendanceEntity entity: attendanceEntity) {
            if (entity.getLogout_date_and_time() == null) {
                entity.setLogout_date_and_time(LocalDateTime.now());

                attendanceRepo.save(entity);
            }
        }

        return ResponseEntity.ok("Logout time added!");

    }

}
