// mariam
package com.example.demo.Repositary;

import com.example.demo.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepo extends JpaRepository<Question, Integer> {
    List<Question> findByQuiz_QuizID(int quizID);
}
