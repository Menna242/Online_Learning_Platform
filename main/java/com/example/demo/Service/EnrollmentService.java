package com.example.demo.Service;

import com.example.demo.Entity.Course;
import com.example.demo.Entity.Enrollment;
import com.example.demo.Entity.Student;
import com.example.demo.Entity.User;
import com.example.demo.Repositary.CourseRepo;
import com.example.demo.Repositary.EnrollmentRepo;
import com.example.demo.Repositary.StudentRepo;
import com.example.demo.Request.CourseRequest;
import com.example.demo.Request.EnrollmentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

@RequiredArgsConstructor
public class EnrollmentService {

    @Autowired
    private  EnrollmentRepo enrollmentRepository;
    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private CourseRepo courseRepo;
    @Autowired
    private NotificationService notificationService;

    // student want to enroll
    public Enrollment createEnrollment(EnrollmentRequest enrollmentRequest) {
        Optional<Student> student = studentRepo.findById(enrollmentRequest.getStudentid());
        Optional<Course> course = courseRepo.findById(enrollmentRequest.getCourseid());
//        System.out.println(student);
//        System.out.println(course);

        if(course.isPresent()&&student.isPresent()){
            Student newStudent = student.get();
            Course newCourse=course.get();

            Enrollment enrollment = new Enrollment();
            enrollment.setCourse(newCourse);
            enrollment.setStudent(newStudent);


            notificationService.createNotification(newStudent, "Enroll in " + newCourse.getTitle(), "SYSTEM");
            User instructor = newCourse.getInstructor();
            notificationService.createNotification(instructor, "Student with id "+newStudent.getId()+" has been enrolled in " + newCourse.getTitle(), "SYSTEM");


            return enrollmentRepository.save(enrollment);
        }
        else{
            return null;
        }


    }

    // list of enrolled students in specific course

    public List<Student> getStudentsByCourseId(Long courseId) {
        List<Enrollment> enrollments = enrollmentRepository.findByCourse_CourseId(courseId);
        return enrollments.stream()
                .map(Enrollment::getStudent) //
                .collect(Collectors.toList());
    }


}