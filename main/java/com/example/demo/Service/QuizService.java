package com.example.demo.Service;

import com.example.demo.Entity.*;
import com.example.demo.Repositary.*;
import com.example.demo.Request.AnswerDTO;
import com.example.demo.Request.QuizRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuizService {

    // to deal with quiz database
    @Autowired
    private QuizRepo quizRepository;

    // to deal with question database
    @Autowired
    private QuestionRepo questionRepository;

    // to deal with course database
    @Autowired
    private CourseRepo courseRepository;

    // to deal with result database
    @Autowired
    private ResultRepo resultRepository;

    // to deal with student database
    @Autowired
    private StudentRepo studentRepository; // Include the student repository

    // initialize quiz repo and question repo to make sure they are not null
    public QuizService(QuizRepo quizRepository, QuestionRepo questionRepository) {
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
    }

    // create quiz by just taking quiz object
    public Quiz createQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }


//    public Quiz getQuizById(int id) {
//        return quizRepository.findById(id).orElse(null);
//    }
//
//    public List<Quiz> getAllQuizzes() {
//        return quizRepository.findAll();
//    }

    // add question
    public Quiz addQuestionsToQuiz(int quizId, Question question) {
        // get quiz optional <question>
        Optional<Quiz> quizOptional = quizRepository.findById(quizId);
        // check if found
        if (quizOptional.isPresent()) {
            Quiz quiz = quizOptional.get();
            question.setQuiz(quiz); // Set foreign key
            questionRepository.save(question); // Save the question
            // ensure it has updated
            return quiz;
        }
        return null;
    }

    // create random quiz
    public Quiz createQuizWithRandomQuestions(long courseId, String quizName, int numQuestions , QuizRequest quizRequest) {
        // check course existence if not [500 error]
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found with ID: " + courseId));

        // get questions in question bank
        List<Question> questionBank = course.getQuestionBank();

        // check that there is enough questions
        if (questionBank.size() < numQuestions) {
            throw new IllegalArgumentException("Not enough questions in the question bank");
        }

        Collections.shuffle(questionBank); // Randomize the questions
        List<Question> selectedQuestions = questionBank.stream().limit(numQuestions).collect(Collectors.toList()); // add the randomized questions there amount = num

        // Create a new quiz with our new id and assign questions
        Quiz quiz = new Quiz();
        quiz.setQuizID(quizRequest.getQuizID());
        quiz.setQuizName(quizName);
        quiz.setQuestions(selectedQuestions);
        selectedQuestions.forEach(question -> question.setQuiz(quiz)); // Set foreign key

        // save updates
        return quizRepository.save(quiz);
    }

    // submit quiz and auto grading and feedback
    public Result submitQuiz(int quizId, long studentId, List<AnswerDTO> studentAnswers) {
        // Retrieve the quiz and student from the database
        // if not found [500 error]
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new IllegalArgumentException("Quiz not found"));

        // if not found [500 error]
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        // initial score
        int score = 0;
        // generate new feedback
        StringBuilder feedback = new StringBuilder();

        // Fetch questions for the quiz
        List<Question> questions = questionRepository.findByQuiz_QuizID(quizId);

        // Iterate over the questions
        for (Question question : questions) {
            // Find the student's answer for this question
            AnswerDTO studentAnswerDTO = studentAnswers.stream()
                    .filter(a -> a.getQuestionID() == question.getQuestionID())
                    .findFirst()
                    .orElse(null);

            // If no answer was provided for this question put in feedback no answer
            if (studentAnswerDTO == null) {
                feedback.append("Question ").append(question.getQuestionID())
                        .append(": No answer provided. Correct Answer: ").append(question.getCorrectAnswer()).append("\n");
                continue;
            }

            // get student answer
            String studentAnswer = studentAnswerDTO.getAnswer();

            // Check if the student's answer matches the correct answer
            if (question.getCorrectAnswer().equals(studentAnswer)) {
                // increment score
                score++;
                // append it in the feedback
                feedback.append("Question ").append(question.getQuestionID()).append(": Correct\n");
            } else {
                // append it in the feedback
                feedback.append("Question ").append(question.getQuestionID())
                        .append(": Incorrect. Correct Answer: ").append(question.getCorrectAnswer()).append("\n");
            }
        }

        // Create the Result object
        Result result = new Result();
        result.setQuiz(quiz);
        result.setStudent(student);  // Ensure student is set here [foreign key]
        result.setScore(score);
        // result.setStudentId(student.getId());
        result.setFeedback(feedback.toString());

        // Save updates
        return resultRepository.save(result);
    }
}
