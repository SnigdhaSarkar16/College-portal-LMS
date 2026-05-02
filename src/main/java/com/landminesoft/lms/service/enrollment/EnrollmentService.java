package com.landminesoft.lms.service.enrollment;

import com.landminesoft.lms.entity.Enrollment;

import java.util.List;

public interface EnrollmentService {

    Enrollment enrollStudent(Long studentId, Long courseId);

    List<Enrollment> getStudentEnrollments(Long studentId);
}