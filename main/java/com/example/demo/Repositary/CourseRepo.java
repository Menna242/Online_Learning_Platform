package com.example.demo.Repositary;

import com.example.demo.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepo extends JpaRepository<Course, Long> {
    List<Course> findByInstructor_Id(Long instructorId);


}
