package com.example.demo.Request;



import com.example.demo.Entity.Course;
import com.example.demo.Entity.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class EnrollmentRequest {

    private long enrollmentId;
    private long studentid;
    private long courseid;

}
