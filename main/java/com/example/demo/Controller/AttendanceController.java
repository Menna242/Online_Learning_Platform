package com.example.demo.Controller;


import com.example.demo.Entity.Attendence;
import com.example.demo.Entity.Course;
import com.example.demo.Entity.Student;


import com.example.demo.Request.AttendenceRequest;
import com.example.demo.Request.CourseRequest;
import com.example.demo.Service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/auth")

@RestController

public class AttendanceController {

    @Autowired
    private AttendanceService attendance_service;

    @PostMapping("/attendence")
    public Attendence registerAtt(@RequestBody AttendenceRequest attendenceRequest) {

        Attendence savedAtt = attendance_service.registerAttendence(attendenceRequest);  //id:2

        if (savedAtt == null) {
            return null;
        }

        return savedAtt;
    }


    @GetMapping("/lessonAttendence/{lessonid}")

    public List<Student> getStudentsByLessonid(@PathVariable Long lessonid) {
        return attendance_service.getStudentsByLessonID(lessonid);
    }

}
