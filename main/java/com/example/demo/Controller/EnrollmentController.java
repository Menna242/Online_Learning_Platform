package com.example.demo.Controller;

import com.example.demo.Entity.Course;


import com.example.demo.Entity.Enrollment;
import com.example.demo.Entity.Student;
import com.example.demo.Request.CourseRequest;
import com.example.demo.Request.EnrollmentRequest;
import com.example.demo.Service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping("/addEnrollment")
    public Enrollment createCourse(@RequestBody EnrollmentRequest enrollmentRequest) {

        Enrollment savedEnrollment = enrollmentService.createEnrollment(enrollmentRequest);
        if(savedEnrollment==null) return  null;//id:2
        return savedEnrollment;
    }

    @GetMapping("/course/{courseId}")
    public List<Student> getStudentsByCourseId(@PathVariable Long courseId) {
        return enrollmentService.getStudentsByCourseId(courseId);
    }

}

