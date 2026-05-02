package com.landminesoft.lms.controller.marks;

import com.landminesoft.lms.entity.Marks;
import com.landminesoft.lms.entity.FacultyPersonal;
import com.landminesoft.lms.entity.Student;
import com.landminesoft.lms.repository.FacultyRepository;
import com.landminesoft.lms.repository.StudentRepository;
import com.landminesoft.lms.service.marks.MarksService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MarksController {

    private final MarksService marksService;
    private final FacultyRepository facultyRepository;
    private final StudentRepository studentRepository;

    public MarksController(MarksService marksService,
                           FacultyRepository facultyRepository,
                           StudentRepository studentRepository) {
        this.marksService = marksService;
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
    }

    // 🔐 FACULTY adds/updates marks
    @PostMapping("/faculty/marks")
    public ResponseEntity<Marks> addMarks(
            @RequestParam Long studentId,
            @RequestParam Long courseId,
            @RequestParam Integer theory,
            @RequestParam Integer practical,
            Authentication authentication
    ) {
        String email = authentication.getName();

        FacultyPersonal faculty = facultyRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Faculty not found"));

        return ResponseEntity.ok(
                marksService.addOrUpdateMarks(studentId, courseId, theory, practical)
        );
    }

    // 🔐 STUDENT views marks
    @GetMapping("/student/marks")
    public ResponseEntity<List<Marks>> getMarks(Authentication authentication) {

        String email = authentication.getName();

        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return ResponseEntity.ok(
                marksService.getStudentMarks(student.getId())
        );
    }
}