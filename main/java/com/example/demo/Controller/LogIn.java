package com.example.demo.Controller;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogIn {
    private String email;
    private String password;
}
