package com.example.demo.Controller;


import com.example.demo.Entity.Course;


import com.example.demo.Entity.lesson;
import com.example.demo.Request.CourseRequest;
import com.example.demo.Service.CourseService;
import com.example.demo.Service.LessonService;
import org.aspectj.apache.bcel.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class CourseController {

    @Autowired
    private CourseService courseService;


    @PostMapping ("/addCourse")
    public Course createCourse(@RequestBody CourseRequest courseRequest) {

        Course savedCourse = courseService.createCourse(courseRequest);  //id:2

        if (savedCourse == null) {
            return null;
        }

        return savedCourse;
    }

    
    @GetMapping("/allCourses")
    public List<Course>getallCourses(){
        return courseService.getAllCourses();
    }


    @GetMapping("/getCourse/{id}")
    public Course getCourseById(@PathVariable Long id) {
        return (courseService.getCourseById(id));
    }

    
    @GetMapping("/course/instructor/{instructorId}")
    public List<Course> getCoursesByInstructorId(@PathVariable Long instructorId) {
        return (courseService.getCoursesByInstructorId(instructorId));
    }

    @DeleteMapping("/deleteCourse/{id}")
    public String deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return "deleted";
    }

    @PutMapping("/update/{id}")
    public Course updateCourse(@PathVariable Long id,@RequestBody CourseRequest courseRequest) {
        return courseService.updateCourse(courseRequest,id);
    }



}
