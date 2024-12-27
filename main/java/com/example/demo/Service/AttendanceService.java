package com.example.demo.Service;





import com.example.demo.Entity.*;
import com.example.demo.Repositary.AttendecnceRepo;
import com.example.demo.Repositary.StudentRepo;
import com.example.demo.Repositary.lessonRepo;
import com.example.demo.Request.AttendenceRequest;
import com.example.demo.Request.CourseRequest;
import com.example.demo.Request.LessonRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class AttendanceService {

    @Autowired
    private AttendecnceRepo attendecnceRepo;

    @Autowired
    private lessonRepo lessonRepo;

    @Autowired
    private StudentRepo studentRepo;

    public Attendence registerAttendence(AttendenceRequest attendenceRequest) {

        Optional<Student> student = studentRepo.findById(attendenceRequest.getStudentid());
        Optional<lesson> lesson = lessonRepo.findById(attendenceRequest.getLessonid());

        if (student.isPresent() && lesson.isPresent()) {

            Student newStd = student.get();
            lesson newlesson = lesson.get();

            if (newlesson.getOtp().equals(attendenceRequest.getOtp())) {
                Attendence attendence = new Attendence();
                attendence.setOtp(attendenceRequest.getOtp());
                attendence.setStudent(newStd);
                attendence.setLesson(newlesson);

                return attendecnceRepo.save(attendence);

            }
            else{
                return null;
            }  }
        else {
            return null;


        }

    }

    public List<Student> getStudentsByLessonID(Long lessonid) {
        List<Attendence> attendences = attendecnceRepo.findByLesson_LessonID(lessonid);
        return attendences.stream()
                .map(Attendence::getStudent) //
                .collect(Collectors.toList());
    }





}






