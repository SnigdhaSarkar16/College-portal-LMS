package com.landminesoft.lms.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "fee_structure")
public class FeeStructure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String branch;
    private Integer semester;

    private Double tuitionFee;
    private Double hostelFee;
    private Double labFee;
}