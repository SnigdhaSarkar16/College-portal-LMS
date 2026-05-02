package com.landminesoft.lms.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "fee_structure")
public class FeeStructure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String branch;
    private Integer semester;

    private BigDecimal tuitionFee;
    private BigDecimal hostelFee;
    private BigDecimal libraryFee;
    private BigDecimal labFee;
    private BigDecimal totalFee;

    private LocalDate dueDate;
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    // getters & setters
    public Long getId() { return id; }
    public String getBranch() { return branch; }
    public Integer getSemester() { return semester; }
    public BigDecimal getTuitionFee() { return tuitionFee; }
    public BigDecimal getHostelFee() { return hostelFee; }
    public BigDecimal getLibraryFee() { return libraryFee; }
    public BigDecimal getLabFee() { return labFee; }
    public BigDecimal getTotalFee() { return totalFee; }
    public LocalDate getDueDate() { return dueDate; }

}