package com.example.demo.Controller;
//Object data transfer for Log in 
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogIn {
    private String email;
    private String password;
}
