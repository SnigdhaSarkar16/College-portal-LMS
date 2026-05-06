package com.landminesoft.lms.controller.student;

import com.landminesoft.lms.dto.request.UpdateStudentDTO;
import com.landminesoft.lms.entity.*;
import com.landminesoft.lms.repository.*;

import com.landminesoft.lms.service.auth.AuthService;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {

    private final AuthService authService;

    private final StudentRepository studentRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final AttendanceRepository attendanceRepository;
    private final MarksRepository marksRepository;

    public StudentController(
            AuthService authService,
            StudentRepository studentRepository,
            EnrollmentRepository enrollmentRepository,
            AttendanceRepository attendanceRepository,
            MarksRepository marksRepository
    ) {
        this.authService = authService;
        this.studentRepository = studentRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.attendanceRepository = attendanceRepository;
        this.marksRepository = marksRepository;
    }

    // PROFILE
    @GetMapping("/profile")
    public Student getProfile(Authentication authentication) {

        String email = authentication.getName();

        return authService.getStudentProfile(email);
    }

    // UPDATE PROFILE
    @PutMapping("/profile")
    public Student updateProfile(
            Authentication authentication,
            @RequestBody UpdateStudentDTO dto
    ) {

        String email = authentication.getName();

        return authService.updateStudentProfile(email, dto);
    }




}