package com.landminesoft.lms.controller;

import com.landminesoft.lms.dto.request.*;
import com.landminesoft.lms.dto.response.RegistrationResponseDTO;
import com.landminesoft.lms.service.auth.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.landminesoft.lms.dto.request.LoginDTO;
import com.landminesoft.lms.dto.response.LoginResponseDTO;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // ---------------- STUDENT ----------------

    @PostMapping("/student/register")
    public ResponseEntity<RegistrationResponseDTO> registerStudent(
            @Valid @RequestBody StudentRegisterDTO dto) {

        return ResponseEntity.ok(authService.registerStudent(dto));
    }

    // ---------------- FACULTY ----------------

    @PostMapping("/faculty/register")
    public ResponseEntity<RegistrationResponseDTO> registerFaculty(
            @Valid @RequestBody FacultyRegisterDTO dto) {

        return ResponseEntity.ok(authService.registerFaculty(dto));
    }

    // ---------------- ADMIN ----------------

    @PostMapping("/admin/register")
    public ResponseEntity<RegistrationResponseDTO> registerAdmin(
            @Valid @RequestBody AdminRegisterDTO dto) {

        return ResponseEntity.ok(authService.registerAdmin(dto));
    }
    @PostMapping("/student/login")
    public ResponseEntity<LoginResponseDTO> loginStudent(
            @Valid @RequestBody LoginDTO dto) {

        return ResponseEntity.ok(authService.loginStudent(dto));
    }

    @PostMapping("/faculty/login")
    public ResponseEntity<LoginResponseDTO> loginFaculty(
            @Valid @RequestBody LoginDTO dto) {

        return ResponseEntity.ok(authService.loginFaculty(dto));
    }

    @PostMapping("/admin/login")
    public ResponseEntity<LoginResponseDTO> loginAdmin(
            @Valid @RequestBody LoginDTO dto) {

        return ResponseEntity.ok(authService.loginAdmin(dto));
    }
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(
            @Valid @RequestBody ForgotPasswordDTO dto) {

        authService.forgotPassword(dto);

        return ResponseEntity.ok(
                java.util.Map.of("message", "Reset link sent to email")
        );
    }
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(
            @Valid @RequestBody ResetPasswordDTO dto) {

        authService.resetPassword(dto);

        return ResponseEntity.ok(
                java.util.Map.of("message", "Password reset successful")
        );
    }
    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(
            Authentication authentication,
            @Valid @RequestBody ChangePasswordDTO dto) {

        String email = authentication.getName();

        authService.changePassword(email, dto);

        return ResponseEntity.ok(
                java.util.Map.of("message", "Password changed successfully")
        );
    }
}