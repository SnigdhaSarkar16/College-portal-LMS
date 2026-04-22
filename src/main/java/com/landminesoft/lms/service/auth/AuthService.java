
package com.landminesoft.lms.service.auth;

import com.landminesoft.lms.dto.request.*;
import com.landminesoft.lms.dto.response.RegistrationResponseDTO;
import com.landminesoft.lms.dto.response.LoginResponseDTO;

public interface AuthService {

    // -------- Registration --------

    RegistrationResponseDTO registerStudent(StudentRegisterDTO dto);

    RegistrationResponseDTO registerFaculty(FacultyRegisterDTO dto);

    RegistrationResponseDTO registerAdmin(AdminRegisterDTO dto);

    // -------- Login --------

    LoginResponseDTO loginStudent(LoginDTO dto);

    LoginResponseDTO loginFaculty(LoginDTO dto);

    LoginResponseDTO loginAdmin(LoginDTO dto);
}