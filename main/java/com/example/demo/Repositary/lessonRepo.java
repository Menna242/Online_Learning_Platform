package com.example.demo.Repositary;

import com.example.demo.Entity.lesson;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface lessonRepo extends JpaRepository<lesson, Long> {

    List<lesson> findByCourse_CourseId(long courseid);

}
