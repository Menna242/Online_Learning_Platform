package com.example.demo.Entity;

import jakarta.persistence.*;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "EnrollmentDB")
@NoArgsConstructor
@AllArgsConstructor
public class Enrollment {

    @Id
    @GeneratedValue
    @Column(name = "ENROLLMENT_ID",nullable = false)
    private Long enrollmentId;

    @ManyToOne
    @JoinColumn(name = "Studnet_ID",referencedColumnName = "USER_ID",nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "C_ID",referencedColumnName = "COURSE_ID",nullable = false)
    private Course course;
}
