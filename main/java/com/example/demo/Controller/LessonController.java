package com.example.demo.Controller;

import com.example.demo.Entity.Course;
import com.example.demo.Entity.lesson;
import com.example.demo.Request.CourseRequest;
import com.example.demo.Request.LessonRequest;
import com.example.demo.Service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @PostMapping("/addLesson")
    public lesson createLesson(@RequestBody LessonRequest lessonRequest) {
        lesson savedLesson = lessonService.createLesson(lessonRequest);
         System.out.println(lessonRequest);

        if (savedLesson == null) {
            return null;
        }

        return savedLesson;
    }


    @DeleteMapping("/deleteLesson/{id}")
    public String deleteLesson(@PathVariable Long id) {
        lessonService.deleteLessonById(id);
        return "Lesson with ID " + id + " has been deleted.";
    }

    @GetMapping("/getLessonsByCourse/{courseid}")
    public List<lesson> getLessonsByCourse(@PathVariable long courseid) {
        return lessonService.getLessonsByCourseId(courseid);
    }





}
