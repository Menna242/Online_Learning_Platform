package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "CourseDB")
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue
    @Column(name = "COURSE_ID")
    private Long courseId;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "DURATION")
    private int duration;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "instructor_id", referencedColumnName = "USER_ID")
    private User instructor;

    // question bank for each course
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Question> questionBank = new ArrayList<>();

    // we don't use it yet
    @ElementCollection
    @CollectionTable(name = "course_media_files", joinColumns = @JoinColumn(name = "course_id"))
    @Column(name = "MEDIA_FILES")
    private List<String> mediaFiles;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<lesson> lessons;

    //    @ElementCollection
//    @CollectionTable(name = "course_media_files", joinColumns = @JoinColumn(name = "course_id"))
//    @Column(name = "MEDIA_FILES")
//    private List<String> mediaFiles;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }



    public List<lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<lesson> lessons) {
        this.lessons = lessons;
    }
}
