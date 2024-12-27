package com.example.demo.Controller;
import com.example.demo.Service.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/performance")
public class PerformanceController {

    // to make functionality with performance service
    @Autowired
    private PerformanceService performanceService;

    // track performance of certain student
    @GetMapping("/student/{studentId}")
    // param path
    public String getPerformance(@PathVariable Long studentId) {
        return performanceService.getStudentPerformance(studentId);
    }
}
