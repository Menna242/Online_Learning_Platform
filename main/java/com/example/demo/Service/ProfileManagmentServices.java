package com.example.demo.Service;

import com.example.demo.Controller.UpdateRequest;
import com.example.demo.Controller.updateAdmin;
import com.example.demo.Entity.Role;
import com.example.demo.Entity.Student;
import com.example.demo.Entity.User;
import com.example.demo.Repositary.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileManagmentServices {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    public  User updateprofile(Long userId , UpdateRequest user) {
        User currUser = userRepo.findById(userId).orElse(null);
        User use = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userId != use.getId()){
            return use;
        }
        if(user.getPassword() != null) {
            currUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        if (user.getEmail() != null) {
            currUser.setEmail(user.getEmail());
        }
        if(user.getName() != null) {
            currUser.setName(user.getName());
        }
        userRepo.save(currUser);
        return currUser;
    }

    public User viewProfile(Long userId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currUser = (User) authentication.getPrincipal();

        if (currUser.get_Role() == Role.STUDENT && !(userId.equals(currUser.getId()))) {
            throw new RuntimeException("Access Denied");

        }
        return getUserWithStudentDetails(userId);
    }

    private User getUserWithStudentDetails(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (user instanceof Student) {
            Student student = (Student) user;
            return student;
        } else {
            return user;
        }
    }

    public User updateAdmin(Long userId, updateAdmin profile) {
        User updatedUser = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        Optional.ofNullable(profile.getGpa()).ifPresent(gpa -> {
            if (updatedUser instanceof Student) {
                ((Student) updatedUser).setGpa(gpa);
            }
        });
        return userRepo.save(updatedUser);
    }

}
