package com.example.demo.Controller;

import com.example.demo.Entity.*;
import com.example.demo.Service.AuthenticationService;
import com.example.demo.Service.CreateUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final CreateUserService createUserService;
    //private final CreateStudentService createStudentService;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody User request
    ){
          if(request.get_Role().equals(Role.ADMIN)){
              return ResponseEntity.ok(authenticationService.registr(request));
          }
          return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new AuthenticationResponse("Error : Only Admins can register"));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody LogIn request
    ){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/CreateStudent")
    public ResponseEntity<AuthenticationResponse> CreateOffical(
            @RequestBody Student request
    ){
        return ResponseEntity.ok(createUserService.CreateStudent(request));
    }
    @PostMapping("/CreateOffical")
    public ResponseEntity<AuthenticationResponse> CreateOffical(
            @RequestBody User request
    ){
        return ResponseEntity.ok(createUserService.CreateOffical(request));
    }

}
