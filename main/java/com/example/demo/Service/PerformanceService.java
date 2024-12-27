package com.example.demo.Service;

import com.example.demo.Entity.Assigment;
import com.example.demo.Entity.Attendence;
import com.example.demo.Entity.Result;
import com.example.demo.Repositary.AssigmentRepo;
import com.example.demo.Repositary.AttendecnceRepo;
import com.example.demo.Repositary.ResultRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerformanceService {
    // to deal with assignment database
    @Autowired
    private AssigmentRepo assignmentRepository;

    // to deal with result database
    @Autowired
    private ResultRepo resultRepository;

    // to deal with attendance database
    @Autowired
    private AttendecnceRepo attendanceRepository;

    // track performance for a certain student
    public String getStudentPerformance(Long studentId) {
        // Fetch Assignments for a student
        List<Assigment> assignments = assignmentRepository.findByStudent_Id(studentId);

        // Fetch Quiz Results for a student
        List<Result> results = resultRepository.findByStudent_Id(studentId);

        // Fetch Attendance for a student
        List<Attendence> attendanceRecords = attendanceRepository.findByStudent_Id(studentId);

        // Aggregate Data (calculate total score\total submission\total score [add all score])
        int totalScore = results.stream().mapToInt(Result::getScore).sum();
        int totalAttendance = attendanceRecords.size();
        int totalAssignments = assignments.size();

        // Build Response
        StringBuilder response = new StringBuilder();
        response.append("Student ID: ").append(studentId)
                .append("\nTotal Assignments Submitted: ").append(totalAssignments)
                .append("\nTotal Quiz Score: ").append(totalScore)
                .append("\nTotal Attendance: ").append(totalAttendance)
                .append("\n");

        // List all quiz IDs and scores
        response.append("\nQuiz Results:\n");
        for (Result result : results) {
            response.append("Quiz ID: ").append(result.getQuiz().getQuizID())
                    .append(", Score: ").append(result.getScore()).append("\n");
        }

        // List all assignment names
        response.append("\nAssignments Submitted:\n");
        for (Assigment assignment : assignments) {
            response.append(assignment.getName()).append("\n");
        }

        // List all lessons attended
        response.append("\nLessons Attended:\n");
        for (Attendence attendance : attendanceRecords) {
            response.append("Lesson: ").append(attendance.getLesson()).append("\n");
        }
        return response.toString();
    }
}
