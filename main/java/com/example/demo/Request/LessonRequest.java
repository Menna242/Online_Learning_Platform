package com.example.demo.Request;

import com.example.demo.Entity.Course;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class LessonRequest {

    private long LessonID;

    private String otp;  //changed database

    private long courseid;

    private String media;

}
