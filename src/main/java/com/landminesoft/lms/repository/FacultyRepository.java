package com.landminesoft.lms.repository;

import com.landminesoft.lms.entity.FacultyPersonal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FacultyRepository extends JpaRepository<FacultyPersonal, Long> {

    Optional<FacultyPersonal> findByEmail(String email);

    boolean existsByEmail(String email);
}