package com.landminesoft.lms.controller.attendance;

import com.landminesoft.lms.entity.Attendance;
import com.landminesoft.lms.entity.FacultyPersonal;
import com.landminesoft.lms.entity.Student;
import com.landminesoft.lms.repository.FacultyRepository;
import com.landminesoft.lms.repository.StudentRepository;
import com.landminesoft.lms.service.attendance.AttendanceService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AttendanceController {

    private final AttendanceService attendanceService;
    private final FacultyRepository facultyRepository;
    private final StudentRepository studentRepository;

    public AttendanceController(AttendanceService attendanceService,
                                FacultyRepository facultyRepository,
                                StudentRepository studentRepository) {
        this.attendanceService = attendanceService;
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
    }

    // 🔐 FACULTY marks attendance
    @PostMapping("/faculty/attendance")
    public ResponseEntity<Attendance> markAttendance(
            @RequestParam Long studentId,
            @RequestParam Long courseId,
            @RequestParam String status,
            @RequestParam String date,
            Authentication authentication
    ) {
        String email = authentication.getName();

        FacultyPersonal faculty = facultyRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Faculty not found"));

        return ResponseEntity.ok(
                attendanceService.markAttendance(
                        faculty.getId(),
                        studentId,
                        courseId,
                        status,
                        LocalDate.parse(date)
                )
        );
    }

    // 🔐 STUDENT views attendance
    @GetMapping("/student/attendance")
    public ResponseEntity<List<Attendance>> getAttendance(Authentication authentication) {

        String email = authentication.getName();

        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return ResponseEntity.ok(
                attendanceService.getStudentAttendance(student.getId())
        );
    }
}