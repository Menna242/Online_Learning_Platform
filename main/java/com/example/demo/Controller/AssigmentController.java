package com.example.demo.Controller;

import com.example.demo.Entity.Assigment;
import com.example.demo.Request.SubmissionRequest;
import com.example.demo.Service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/assignment")
public class AssigmentController {

    // to make functionality
    @Autowired
    private AssignmentService assignmentService;

    // Submit Assignment
    @PostMapping("/{assignmentId}/submit/{studentId}")
    // take param from path\body and deal with request
    public Assigment submitAssignment(@PathVariable Long assignmentId, @RequestBody SubmissionRequest submissionRequest, @PathVariable Long studentId) {
        return assignmentService.submitAssignment(assignmentId, submissionRequest.getSubmission(),studentId);
    }

    // Grade Assignment and feedback
    @PostMapping("/{assignmentId}/grade")
    // takes param from path/param in postman
    public Assigment gradeAssignment(@PathVariable Long assignmentId, @RequestParam Integer grade,
                                     @RequestParam String feedback) {
        return assignmentService.gradeAssignment(assignmentId, grade, feedback);
    }

    // create assignment
    @PostMapping("/create")
    // take param from body
    public Assigment createAssignment(@RequestBody Assigment assignment) {
        return assignmentService.createAssignment(assignment);
    }
}