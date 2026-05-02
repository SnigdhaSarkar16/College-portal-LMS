package com.landminesoft.lms.controller.course;

import com.landminesoft.lms.entity.Course;
import com.landminesoft.lms.service.course.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // 🔐 FACULTY ONLY
    @PostMapping("/faculty/courses")
    public ResponseEntity<Course> createCourse(
            @RequestParam Long subjectId,
            @RequestBody Course course,
            Authentication authentication
    ) {
        // email from JWT
        String email = authentication.getName();

        // ⚠️ for now we assume facultyId = 1 (temporary)
        // we will fix this later properly
        Long facultyId = 1L;

        return ResponseEntity.ok(
                courseService.createCourse(facultyId, subjectId, course)
        );
    }

    // 🌍 PUBLIC
    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }
}