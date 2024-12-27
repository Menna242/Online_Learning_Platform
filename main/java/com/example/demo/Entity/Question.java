package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "QuestionDB")
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Id
    @Column(name = "QuestionID")
    private int questionID;
    @Column(name = "questionText")
    private String questionText;
    @Column(name = "correctAnswer")
    private String correctAnswer;
    @ManyToOne
    @JoinColumn(name = "QuizID", nullable = false)
    private Quiz quiz;
    @ManyToOne
    @JoinColumn(name = "COURSE_ID", nullable = false)
    private Course course;

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
};
