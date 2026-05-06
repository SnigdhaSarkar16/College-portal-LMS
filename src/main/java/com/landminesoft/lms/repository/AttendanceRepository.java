package com.landminesoft.lms.repository;

import com.landminesoft.lms.entity.Attendance;
import com.landminesoft.lms.entity.Student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    List<Attendance> findByStudentId(Long studentId);

    boolean existsByStudentIdAndCourseIdAndClassDate(
            Long studentId,
            Long courseId,
            LocalDate classDate
    );

    // ✅ Needed for frontend dashboard
    List<Attendance> findByStudent(Student student);
}