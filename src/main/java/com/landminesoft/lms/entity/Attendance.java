package com.landminesoft.lms.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔗 Student
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    // 🔗 Course
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    private LocalDate classDate;

    private String status; // PRESENT / ABSENT / LEAVE

    // 🔗 Faculty who marked
    @ManyToOne
    @JoinColumn(name = "marked_by", nullable = false)
    private FacultyPersonal markedBy;

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    // ===== GETTERS & SETTERS =====

    public Long getId() { return id; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }

    public LocalDate getClassDate() { return classDate; }
    public void setClassDate(LocalDate classDate) { this.classDate = classDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public FacultyPersonal getMarkedBy() { return markedBy; }
    public void setMarkedBy(FacultyPersonal markedBy) { this.markedBy = markedBy; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}