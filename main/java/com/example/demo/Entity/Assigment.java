package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "AssigmentDB")
@NoArgsConstructor
@AllArgsConstructor
public class Assigment {
    @Id
    @Column(name = "AssignID")
    private Long assignID;
    @Column(name = "name")
    private String name;
    @Column(name = "Feedback")
    private String feedback;
    
    @Column(name = "submission", nullable = true)
    private String submission; // URL or path to the submitted file

    @Column(name = "grade", nullable = true)
    private Integer grade; // Grade assigned to the assignment

    @ManyToOne
    @JoinColumn(name = "USER_ID") // Foreign key in AssigmentDB
    private Student student;

    public Long getAssignID() {
        return assignID;
    }

    public void setAssignID(Long assignID) {
        this.assignID = assignID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
