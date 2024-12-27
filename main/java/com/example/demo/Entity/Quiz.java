package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "QuizDB")
@NoArgsConstructor
@AllArgsConstructor
public class Quiz {
    @Id
    @Column(name = "QuizID")
    private int quizID;
    @Column(name = "QuizName")
    private String QuizName;
    @OneToMany(mappedBy = "questionID", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Question> Questions;

    public int getQuizID() {
        return quizID;
    }

    public void setQuizID(int quizID) {
        this.quizID = quizID;
    }

    public String getQuizName() {
        return QuizName;
    }

    public void setQuizName(String quizName) {
        QuizName = quizName;
    }

    public List<Question> getQuestions() {
        return Questions;
    }

    public void setQuestions(List<Question> questions) {
        Questions = questions;
    }
}
