package com.landminesoft.lms.repository;

import com.landminesoft.lms.entity.Marks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MarksRepository extends JpaRepository<Marks, Long> {

    List<Marks> findByStudentId(Long studentId);

    Optional<Marks> findByStudentIdAndCourseId(Long studentId, Long courseId);
}