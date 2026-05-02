package com.landminesoft.lms.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "enrollment")
public class Enrollment {

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

    // 🔗 Subject
    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    private Integer semester;

    private String academicYear;

    private String status; // ACTIVE, DROPPED, COMPLETED

    private LocalDateTime enrolledDate;

    @PrePersist
    public void prePersist() {
        this.enrolledDate = LocalDateTime.now();
        this.status = "ACTIVE";
    }

    // ===== GETTERS & SETTERS =====

    public Long getId() { return id; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }

    public Subject getSubject() { return subject; }
    public void setSubject(Subject subject) { this.subject = subject; }

    public Integer getSemester() { return semester; }
    public void setSemester(Integer semester) { this.semester = semester; }

    public String getAcademicYear() { return academicYear; }
    public void setAcademicYear(String academicYear) { this.academicYear = academicYear; }

    public String getStatus() { return status; }

    public LocalDateTime getEnrolledDate() { return enrolledDate; }
}