package com.landminesoft.lms.service.auth;

import com.landminesoft.lms.dto.request.*;
import com.landminesoft.lms.dto.response.RegistrationResponseDTO;
import com.landminesoft.lms.dto.response.LoginResponseDTO;
import com.landminesoft.lms.entity.Student;

public interface AuthService {

    // -------- Registration --------
    RegistrationResponseDTO registerStudent(StudentRegisterDTO dto);
    RegistrationResponseDTO registerFaculty(FacultyRegisterDTO dto);
    RegistrationResponseDTO registerAdmin(AdminRegisterDTO dto);

    // -------- Login --------
    LoginResponseDTO loginStudent(LoginDTO dto);
    LoginResponseDTO loginFaculty(LoginDTO dto);
    LoginResponseDTO loginAdmin(LoginDTO dto);

    // -------- Password Reset --------
    void forgotPassword(ForgotPasswordDTO dto);
    void resetPassword(ResetPasswordDTO dto);
    void changePassword(String email, ChangePasswordDTO dto);

    // -------- Profile --------
    Student getStudentProfile(String email);   // ✅ NEW
    Student updateStudentProfile(String email, UpdateStudentDTO dto);
}