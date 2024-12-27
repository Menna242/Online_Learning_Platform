// mariam
package com.example.demo.Repositary;
import com.example.demo.Entity.Assigment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AssigmentRepo extends JpaRepository<Assigment, Long> {
    List<Assigment> findByStudent_Id(Long studentId);
}
