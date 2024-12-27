package com.example.demo.Repositary;

import com.example.demo.Entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepo extends JpaRepository<Result, Long> {
    List<Result> findByStudent_Id(Long studentId);
}
