package com.landminesoft.lms.controller.student;

import com.landminesoft.lms.entity.Student;
import com.landminesoft.lms.service.auth.AuthService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.landminesoft.lms.dto.request.UpdateStudentDTO;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final AuthService authService;

    public StudentController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/profile")
    public Student getProfile(Authentication authentication) {

        String email = authentication.getName(); // from JWT

        return authService.getStudentProfile(email);
    }
    @PutMapping("/profile")
    public Student updateProfile(Authentication authentication,
                                 @RequestBody UpdateStudentDTO dto) {

        String email = authentication.getName();

        return authService.updateStudentProfile(email, dto);
    }
}
