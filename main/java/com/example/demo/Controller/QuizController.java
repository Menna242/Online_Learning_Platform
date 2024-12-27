package com.example.demo.Controller;
import com.example.demo.Entity.Quiz;
import com.example.demo.Entity.Question;
import com.example.demo.Entity.Result;
import com.example.demo.Request.AnswerDTO;
import com.example.demo.Request.QuizRequest;
import com.example.demo.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class QuizController {

    // to make functionallity
    @Autowired
    private QuizService quizService;

    // add questions
    @PostMapping("/{quizId}/add-question")
    // param in path\body take question object
    public Quiz addQuestionToQuiz(@PathVariable int quizId, @RequestBody Question question) {
        return quizService.addQuestionsToQuiz(quizId, question);
    }

    // create quiz
    @PostMapping("/create")
    // param body take quiz object
    public Quiz createQuiz(@RequestBody Quiz quiz) {
        return quizService.createQuiz(quiz);
    }

    // create random question quiz
    @PostMapping("/course/{courseId}/create-quiz")
    // param path\param from request param in postman
    public Quiz createQuizWithRandomQuestions(
            @PathVariable int courseId,
            @RequestParam String quizName,
            @RequestParam int numQuestions, @RequestBody QuizRequest quizRequest) {
        return quizService.createQuizWithRandomQuestions(courseId, quizName, numQuestions,quizRequest);
    }

    // submit quiz and auto grading and auto feedback
    @PostMapping("/{quizId}/submit")
    // param path\param from request param in postman\body (as a list not map)
    public Result submitQuiz(@PathVariable int quizId, @RequestParam Long studentId, @RequestBody List<AnswerDTO> answers) {
        return quizService.submitQuiz(quizId, studentId, answers);
    }

}
