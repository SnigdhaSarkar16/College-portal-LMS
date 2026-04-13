package com.landminesoft.lms.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "marks")
public class Marks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    private Integer theory;
    private Integer practical;
    private Integer total;
    private String grade;
}