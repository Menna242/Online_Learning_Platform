package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "ResultDB")
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // map student id to certain result
    @ManyToOne
    @JoinColumn(name = "Student_id", nullable = true)
    private Student student;

    // map quiz to certain result
    @ManyToOne
    @JoinColumn(name = "QuizID",referencedColumnName = "QuizID", nullable = false)
    private Quiz quiz;

    // automatic grading
    @Column(name = "Score")
    private int score;

    // automatic feedback
    @Column(name = "Feedback")
    private String feedback;

}
