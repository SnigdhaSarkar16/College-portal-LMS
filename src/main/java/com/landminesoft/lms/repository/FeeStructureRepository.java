package com.landminesoft.lms.repository;

import com.landminesoft.lms.entity.FeeStructure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeeStructureRepository extends JpaRepository<FeeStructure, Long> {

    Optional<FeeStructure> findByBranchAndSemester(String branch, Integer semester);
}