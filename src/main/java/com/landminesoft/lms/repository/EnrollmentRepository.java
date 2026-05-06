package com.landminesoft.lms.repository;

import com.landminesoft.lms.entity.Enrollment;
import com.landminesoft.lms.entity.Student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    // Existing methods
    List<Enrollment> findByStudentId(Long studentId);

    boolean existsByStudentIdAndCourseId(Long studentId, Long courseId);

    // ✅ Needed for frontend dashboard
    List<Enrollment> findByStudent(Student student);
}