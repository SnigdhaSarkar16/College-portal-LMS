package com.landminesoft.lms.controller.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/api/admin/test")
    public String adminTest() {
        return "Admin access success";
    }

    @GetMapping("/api/student/test")
    public String studentTest() {
        return "Student access success";
    }
}