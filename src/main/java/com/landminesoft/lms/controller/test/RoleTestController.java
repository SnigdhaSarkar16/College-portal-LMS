package com.landminesoft.lms.controller.test;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RoleTestController {

    @GetMapping("/student/enrollments")
    public String studentOnly() {
        return "Student access OK";
    }

    @GetMapping("/faculty/students")
    public String facultyOnly() {
        return "Faculty access OK";
    }

    @GetMapping("/admin/reports")
    public String adminOnly() {
        return "Admin access OK";
    }
}