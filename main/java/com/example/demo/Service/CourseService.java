package com.example.demo.Service;
import com.example.demo.Entity.*;
import com.example.demo.Repositary.CourseRepo;
import com.example.demo.Repositary.UserRepo;
import com.example.demo.Repositary.lessonRepo;
import com.example.demo.Request.CourseRequest;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {

    @Autowired
    private CourseRepo courseRepo;
    @Autowired
    private UserRepo userRepository;
    @Autowired
    private EnrollmentService enrollmentService;
    @Autowired
    private NotificationService notificationService;


    public Course createCourse(CourseRequest courseRequest) {
        Long longObj = courseRequest.getInstructorId();
        long myid = longObj.longValue();

        Optional<User> instructor = userRepository.findById(myid);




        if (instructor.isPresent()) {

            User newInstructor = instructor.get();

            Course course = new Course();
            course.setTitle(courseRequest.getTitle());
            course.setDescription(courseRequest.getDescription());
            course.setDuration(courseRequest.getDuration());
            course.setInstructor(newInstructor);



            return courseRepo.save(course);

        } else {
            return null;
        }
    }


    public void deleteCourse(long id) {
        courseRepo.deleteById(id);
    }
    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }

    public Course getCourseById(Long id) {
        return  courseRepo.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));
    }
    public List<Course> getCoursesByInstructorId(Long instructorId) {
        return courseRepo.findByInstructor_Id(instructorId);
    }


    public Course updateCourse(CourseRequest courseRequest,Long id) {

        Optional<Course> existingCourse = courseRepo.findById(id);

        if (existingCourse.isPresent()) {
            Course course = existingCourse.get();

            if (courseRequest.getTitle() != null) {
                course.setTitle(courseRequest.getTitle());
            }
            if (courseRequest.getDescription() != null) {
                course.setDescription(courseRequest.getDescription());
            }
            if (courseRequest.getDuration() != 0) {
                course.setDuration(courseRequest.getDuration());
            }

            if (courseRequest.getInstructorId() != null) {
                Optional<User> instructor = userRepository.findById(courseRequest.getInstructorId());
                if (instructor.isPresent()) {
                    course.setInstructor(instructor.get());
                }
            }

            courseRepo.save(course);

            sendNotificationToStudent(course);

            return course;
        } else {
            return null;
        }
    }

    private void sendNotificationToStudent(Course course) {

        List<Student> students = enrollmentService.getStudentsByCourseId(course.getCourseId());

        for (User student : students) {
            String content = "Updated" + course.getTitle();
            notificationService.createNotification(student, content,"SYSTEM");
        }
    }



}
