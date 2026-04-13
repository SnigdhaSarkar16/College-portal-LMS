package com.landminesoft.lms.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "announcement")
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 2000)
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Target target;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public enum Target {
        STUDENT,
        FACULTY,
        ALL
    }

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
    }
}