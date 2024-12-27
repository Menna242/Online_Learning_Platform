package com.example.demo.Service;


import com.example.demo.Entity.Course;
import com.example.demo.Entity.User;
import com.example.demo.Entity.lesson;
import com.example.demo.Repositary.CourseRepo;
import com.example.demo.Repositary.lessonRepo;
import com.example.demo.Request.CourseRequest;
import com.example.demo.Request.LessonRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LessonService {
    @Autowired
    private lessonRepo lessonRepo;
    @Autowired
    private CourseRepo courseRepo;

    public lesson createLesson(LessonRequest lessonRequest) {
        Optional<Course> course = courseRepo.findById(lessonRequest.getCourseid());

        if (course.isPresent()) {
            Course newcourse = course.get();

            lesson mylesson = new lesson();
            mylesson.setLessonID(lessonRequest.getLessonID());
            mylesson.setOtp(lessonRequest.getOtp());
            mylesson.setCourse(newcourse);
            mylesson.setMedia(lessonRequest.getMedia());


            return lessonRepo.save(mylesson);

        } else {
            return null;
        }
    }

 // delete lesson
    public void deleteLessonById(Long id) {
         lessonRepo.deleteById(id);
    }
    public List<lesson> getLessonsByCourseId(long courseid) {

        return lessonRepo.findByCourse_CourseId(courseid);
    }


}
