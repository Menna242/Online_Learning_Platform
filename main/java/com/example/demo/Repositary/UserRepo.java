package com.example.demo.Repositary;

import com.example.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface  UserRepo extends JpaRepository <User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findById(long id);
}
