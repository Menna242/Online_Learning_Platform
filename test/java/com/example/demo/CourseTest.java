package com.example.demo;

import com.example.demo.Entity.*;
import com.example.demo.Repositary.CourseRepo;
import com.example.demo.Repositary.UserRepo;
import com.example.demo.Request.CourseRequest;
import com.example.demo.Service.CourseService;
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


public class CourseTest {

    @Mock
    private CourseRepo courseRepo;

    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private CourseService courseService;

    @BeforeEach
    public void setup() {
        // Initializes mocks and injects them
        MockitoAnnotations.openMocks(this); 
    }

    @Test
    public void testCreationCourse_Success() {

        User instructor = new User();
        instructor.setName("Aya");
        instructor.setId(2L);
        instructor.setEmail("aya@gmail.com");
        instructor.setPassword("123456");
        instructor.set_Role(Role.INSTRUCTOR);

        CourseRequest courseRequest = new CourseRequest();
        courseRequest.setTitle("ProgrammingCourse");
        courseRequest.setDescription("C++ concepts");
        courseRequest.setDuration(80);
        courseRequest.setInstructorId(2L);
        courseRequest.setCourseId(1L);


        when(userRepo.findById(2L)).thenReturn(Optional.of(instructor));

        Course mockCourse = new Course();
        mockCourse.setTitle("ProgrammingCourse");
        mockCourse.setCourseId(1L);
        mockCourse.setDuration(80);
        mockCourse.setDescription("C++ concepts");
        mockCourse.setInstructor(instructor);


        when(courseRepo.save(any(Course.class))).thenReturn(mockCourse);


        Course course = courseService.createCourse(courseRequest);

        assertNotNull(course, "Course should not be null");
        assertEquals("ProgrammingCourse", course.getTitle(), "Course title should match");
        assertEquals("C++ concepts", course.getDescription(), "Course description should match");
        assertEquals(80, course.getDuration(), "Course duration should match");
        assertEquals(instructor, course.getInstructor(), "Instructor should match");


        verify(courseRepo, times(1)).save(any(Course.class));
    }
//-----------------------------------------------------------------------------------------------------------------------------------------

    @Test
    public void findAllCourses_WithInstructorID(){

        User instructor = new User();
        instructor.setName("Aya");
        instructor.setId(2L);
        instructor.setEmail("aya@gmail.com");
        instructor.setPassword("123456");
        instructor.set_Role(Role.INSTRUCTOR);

        when(userRepo.findById(2L)).thenReturn(Optional.of(instructor));

        Course course = new Course();
        course.setTitle("ProgrammingCourse");
        course.setCourseId(1L);
        course.setDuration(80);
        course.setDescription("C++ concepts");
        course.setInstructor(instructor);

        List<Course> mockcourses = new ArrayList<>();
        mockcourses.add(course);
        when(courseRepo.findByInstructor_Id(2L)).thenReturn(mockcourses);

        List<Course>courses=courseService.getCoursesByInstructorId(instructor.getId());
        assertTrue(courses.contains(course));

       verify(courseRepo, times(1)).findByInstructor_Id(course.getInstructor().getId());




    }


}
