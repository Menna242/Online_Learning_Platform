package com.example.demo;

import com.example.demo.Entity.*;
import com.example.demo.Repositary.CourseRepo;
import com.example.demo.Repositary.EnrollmentRepo;
import com.example.demo.Repositary.StudentRepo;
import com.example.demo.Request.EnrollmentRequest;
import com.example.demo.Service.EnrollmentService;
import com.example.demo.Service.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EnrollmentTest {

    @Mock
    private EnrollmentRepo enrollmentRepo;

    @Mock
    private StudentRepo studentRepo;

    @Mock
    private CourseRepo courseRepo;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private EnrollmentService enrollmentService; // Inject mocks into this

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Initializes mocks and injects them
    }

    @Test
    public void testCreateEnrollment_Success() {
        // step1: Mocking Student
        Student student = new Student();
        student.setName("Aya");
        student.setId(2L);
        student.setEmail("aya@gmail.com");
        student.setPassword("123456");
        student.set_Role(Role.STUDENT);
        student.setGpa(4);

        // step2: Mocking Instructor
        User instructor = new User();
        instructor.setName("Menna");
        instructor.set_Role(Role.INSTRUCTOR);
        instructor.setId(1L);
        instructor.setEmail("menna@gmail.com");
        instructor.setPassword("123456");

        // step3: Mocking Course
        Course course = new Course();
        course.setTitle("software");
        course.setCourseId(1L);
        course.setDuration(40);
        course.setDescription("software by using JAVA");
        course.setInstructor(instructor);

        // step4: Mocking Request
        EnrollmentRequest request = new EnrollmentRequest();
        request.setStudentid(2L);
        request.setCourseid(1L);
        request.setEnrollmentId(1L);

        // Mocking Repository Responses
        when(studentRepo.findById(2L)).thenReturn(Optional.of(student));
        when(courseRepo.findById(1L)).thenReturn(Optional.of(course));

        Enrollment mockEnrollment = new Enrollment();
        mockEnrollment.setStudent(student);
        mockEnrollment.setCourse(course);
        mockEnrollment.setEnrollmentId(1L);

        when(enrollmentRepo.save(any(Enrollment.class))).thenReturn(mockEnrollment);

        // Mocking NotificationService
        doNothing().when(notificationService).createNotification(any(User.class), anyString(), anyString());

        // Execute Service
        Enrollment enrollment = enrollmentService.createEnrollment(request);


        assertEquals(student, enrollment.getStudent());
        assertEquals(course, enrollment.getCourse());
        assertEquals(1L, enrollment.getEnrollmentId());

        // Verify Interactions
        verify(enrollmentRepo, times(1)).save(any(Enrollment.class));

        verify(notificationService).createNotification(
                student,
                "Enroll in software",
                "SYSTEM"
        );

//         Verify Instructor Notification
        verify(notificationService).createNotification(
                instructor,
                "Student with id 2 has been enrolled in software",
                "SYSTEM"
        );

        // Verify Repository Save Call
        verify(enrollmentRepo, times(1)).save(any(Enrollment.class));
    }

    @Test
    public void testFindAllStudentInCourseId() {
        // step1: Mocking Student
        Student student = new Student();
        student.setName("Aya");
        student.setId(2L);
        student.setEmail("aya@gmail.com");
        student.setPassword("123456");
        student.set_Role(Role.STUDENT);
        student.setGpa(4);

        //  step2:Mocking Instructor
        User instructor = new User();
        instructor.setName("Menna");
        instructor.set_Role(Role.INSTRUCTOR);
        instructor.setId(1L);
        instructor.setEmail("menna@gmail.com");
        instructor.setPassword("123456");

        //  step3:Mocking Course
        Course course = new Course();
        course.setTitle("software");
        course.setCourseId(1L);
        course.setDuration(40);
        course.setDescription("software by using JAVA");
        course.setInstructor(instructor);

        // step4: Mocking Request
        EnrollmentRequest request = new EnrollmentRequest();
        request.setStudentid(2L);
        request.setCourseid(1L);
        request.setEnrollmentId(1L);

        // Mocking Repository Responses
        when(studentRepo.findById(2L)).thenReturn(Optional.of(student));
        when(courseRepo.findById(1L)).thenReturn(Optional.of(course));

        // Create the Enrollment
        Enrollment mockEnrollment = new Enrollment();
        mockEnrollment.setStudent(student);
        mockEnrollment.setCourse(course);
        mockEnrollment.setEnrollmentId(1L);

        // Mock enrollmentRepo.findByCourse_CourseId -> return list of enrollments
        List<Enrollment> enrollmentList = new ArrayList<>();
        enrollmentList.add(mockEnrollment);
        when(enrollmentRepo.findByCourse_CourseId(course.getCourseId())).thenReturn(enrollmentList);

        // Execute Service Method of getAllStudents ByCourseId
        List<Student> result = enrollmentService.getStudentsByCourseId(course.getCourseId());

        assertTrue(result.contains(student));

        // Verify interactions
        verify(enrollmentRepo, times(1)).findByCourse_CourseId(course.getCourseId()); // Correct interaction
    }


}