package com.example.demo.Service;

import com.example.demo.Entity.Student;
import com.example.demo.Repositary.AssigmentRepo;

import com.example.demo.Repositary.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Entity.Assigment;

@Service
public class AssignmentService {

    // to deal with assignment database
    @Autowired
    private AssigmentRepo assignmentRepository;

    // to deal with student database
    @Autowired
    private StudentRepo studentRepository;

    // Assignment Submission
    public Assigment submitAssignment(Long assignmentId, String submission,Long studentId) {
        // check that this assignment found if not throw exception [500 error]
        Assigment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new IllegalArgumentException("Assignment not found"));

        // Retrieve the student by studentId
        // check existence of student if not throw exception [500 error]
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        // Set the student for the assignment ass a foreign key
        assignment.setStudent(student);
        // set submission [url of file]
        assignment.setSubmission(submission);
        // save updates
        return assignmentRepository.save(assignment);
    }

    // manual Feedback and Grading
    public Assigment gradeAssignment(Long assignmentId, Integer grade, String feedback) {
        // check that this assignment found if not throw exception [500 error]
        Assigment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new IllegalArgumentException("Assignment not found"));
        // update database by putting grade and feedback
        assignment.setGrade(grade);
        assignment.setFeedback(feedback);
        // save updates
        return assignmentRepository.save(assignment);
    }

    // Add Assignment
    public Assigment createAssignment(Assigment assignment) {
        // just save updates [object of assignment]
        return assignmentRepository.save(assignment);
    }
}
