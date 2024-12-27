package com.example.demo.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CourseRequest {

    private Long courseId;
    private String title;
    private String description;
    private int duration;
    private Long instructorId;

}
