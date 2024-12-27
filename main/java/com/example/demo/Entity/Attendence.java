package com.example.demo.Entity;

import com.example.demo.Repositary.StudentRepo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@Table(name = "Attendance")
@NoArgsConstructor
@AllArgsConstructor
public class Attendence {

    @Id
    @GeneratedValue
    @Column(name = "ATTEND_ID")
    private long id;

    @ManyToOne
    @JoinColumn(name = "Studnet_ID",referencedColumnName = "USER_ID",nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "lesson_id",referencedColumnName = "LESSON_ID",nullable = false)
    private lesson lesson;

    @Column(name = "OTP", nullable = false)
    private String otp;  // OTP entered by student



    
}
