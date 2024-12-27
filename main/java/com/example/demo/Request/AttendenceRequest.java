package com.example.demo.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AttendenceRequest {
    private long id;
    private long studentid;
    private long lessonid;
    private String otp;  // OTP entered by student

  

}
