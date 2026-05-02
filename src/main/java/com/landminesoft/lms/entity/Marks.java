package com.landminesoft.lms.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "marks")
public class Marks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔗 Student
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    // 🔗 Subject
    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    // 🔗 Course
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    private Integer theoryMarks;
    private Integer practicalMarks;
    private Integer totalMarks;
    private String grade;

    private Integer semester;
    private String academicYear;

    private LocalDateTime updatedAt;

    @PrePersist
    @PreUpdate
    public void prePersist() {
        this.updatedAt = LocalDateTime.now();
    }

    // ===== GETTERS & SETTERS =====

    public Long getId() { return id; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public Subject getSubject() { return subject; }
    public void setSubject(Subject subject) { this.subject = subject; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }

    public Integer getTheoryMarks() { return theoryMarks; }
    public void setTheoryMarks(Integer theoryMarks) { this.theoryMarks = theoryMarks; }

    public Integer getPracticalMarks() { return practicalMarks; }
    public void setPracticalMarks(Integer practicalMarks) { this.practicalMarks = practicalMarks; }

    public Integer getTotalMarks() { return totalMarks; }
    public void setTotalMarks(Integer totalMarks) { this.totalMarks = totalMarks; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    public Integer getSemester() { return semester; }
    public void setSemester(Integer semester) { this.semester = semester; }

    public String getAcademicYear() { return academicYear; }
    public void setAcademicYear(String academicYear) { this.academicYear = academicYear; }
}