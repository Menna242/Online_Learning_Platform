package com.example.demo.Service;


import com.example.demo.Controller.AuthenticationResponse;
import com.example.demo.Entity.Role;
import com.example.demo.Entity.Student;
import com.example.demo.Entity.User;
import com.example.demo.Repositary.StudentRepo;
import com.example.demo.Repositary.UserRepo;
import com.example.demo.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserService {
    private final UserRepo userRepo;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final StudentRepo studentRepo;
    ///Create Admin or instructor or any official at the website
    public AuthenticationResponse CreateOffical(User request) {
        var user = User.builder()
                .id(request.getId())
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                ._Role(request.get_Role())
                .build();
        userRepo.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse CreateStudent(Student request) {
        Student student = new Student();
        student.setId(request.getId());
        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setPassword(passwordEncoder.encode(request.getPassword()));
        student.setGpa(request.getGpa());
        student.set_Role(Role.STUDENT);
        studentRepo.save(student);
        var jwtToken = jwtService.generateToken(student);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
