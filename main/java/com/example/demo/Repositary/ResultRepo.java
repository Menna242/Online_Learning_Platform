// mariam
package com.example.demo.Repositary;

import com.example.demo.Entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepo extends JpaRepository<Result, Long> {

//    // Custom query to fetch results by quiz ID
//    List<Result> findByQuiz_QuizID(int quizId);
//
//    // Custom query to fetch results by score range
//    List<Result> findByScoreBetween(int minScore, int maxScore);
//
//    // Fetch results with specific feedback
//    List<Result> findByFeedbackContaining(String feedbackKeyword);

    List<Result> findByStudent_Id(Long studentId);
}
