package com.example.demo.Repositary;

import com.example.demo.Entity.Assigment;
import com.example.demo.Entity.Attendence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendecnceRepo extends JpaRepository<Attendence , Long> {
    List<Attendence> findByStudent_Id(Long studentId);

    List<Attendence> findByLesson_LessonID(Long lessonId);


}
