package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@Table(name = "lessonDB")
@NoArgsConstructor
@AllArgsConstructor
public class lesson {

    @Id
    @GeneratedValue
    @Column(name = "LESSON_ID")
    private long lessonID;

    @Column(name = "OTPID", nullable = false)
    private String otp;
    //changed database

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "COURSE_ID", referencedColumnName = "COURSE_ID", nullable = false)
    @JsonBackReference
    private Course course;


    @Column(name = "Media")
    private String media;



};
