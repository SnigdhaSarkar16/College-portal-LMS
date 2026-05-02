package com.landminesoft.lms.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "com/landminesoft/lms/service/subject")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String subjectCode;

    @Column(nullable = false)
    private String subjectName;

    @Column(nullable = false)
    private String branch;

    @Column(nullable = false)
    private Integer semester;

    private Integer credits;

    private Integer theoryMarks;

    private Integer practicalMarks;

    private LocalDateTime createdAt;

    // Auto set timestamp
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    // ===== GETTERS & SETTERS =====

    public Long getId() { return id; }

    public String getSubjectCode() { return subjectCode; }
    public void setSubjectCode(String subjectCode) { this.subjectCode = subjectCode; }

    public String getSubjectName() { return subjectName; }
    public void setSubjectName(String subjectName) { this.subjectName = subjectName; }

    public String getBranch() { return branch; }
    public void setBranch(String branch) { this.branch = branch; }

    public Integer getSemester() { return semester; }
    public void setSemester(Integer semester) { this.semester = semester; }

    public Integer getCredits() { return credits; }
    public void setCredits(Integer credits) { this.credits = credits; }

    public Integer getTheoryMarks() { return theoryMarks; }
    public void setTheoryMarks(Integer theoryMarks) { this.theoryMarks = theoryMarks; }

    public Integer getPracticalMarks() { return practicalMarks; }
    public void setPracticalMarks(Integer practicalMarks) { this.practicalMarks = practicalMarks; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}