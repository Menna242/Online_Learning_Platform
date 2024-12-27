// mariam
package com.example.demo.Repositary;

import com.example.demo.Entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepo extends JpaRepository<Quiz, Integer> {
}
