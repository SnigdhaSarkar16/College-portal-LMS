package com.landminesoft.lms.controller.enrollment;

import com.landminesoft.lms.entity.Enrollment;
import com.landminesoft.lms.service.enrollment.EnrollmentService;
import com.landminesoft.lms.repository.StudentRepository;
import com.landminesoft.lms.entity.Student;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;
    private final StudentRepository studentRepository;

    public EnrollmentController(EnrollmentService enrollmentService,
                                StudentRepository studentRepository) {
        this.enrollmentService = enrollmentService;
        this.studentRepository = studentRepository;
    }

    // 🔐 STUDENT enroll
    @PostMapping("/enroll")
    public ResponseEntity<Enrollment> enroll(
            @RequestParam Long courseId,
            Authentication authentication
    ) {
        String email = authentication.getName();

        // get student from JWT email
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return ResponseEntity.ok(
                enrollmentService.enrollStudent(student.getId(), courseId)
        );
    }

    // 🔐 STUDENT view own enrollments
    @GetMapping("/enrollments")
    public ResponseEntity<List<Enrollment>> getEnrollments(Authentication authentication) {

        String email = authentication.getName();

        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return ResponseEntity.ok(
                enrollmentService.getStudentEnrollments(student.getId())
        );
    }
}