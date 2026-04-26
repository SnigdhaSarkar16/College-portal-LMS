package com.landminesoft.lms.service.auth;

import com.landminesoft.lms.dto.request.*;
import com.landminesoft.lms.dto.response.RegistrationResponseDTO;
import com.landminesoft.lms.dto.response.LoginResponseDTO;
import com.landminesoft.lms.entity.*;
import com.landminesoft.lms.repository.*;
import com.landminesoft.lms.security.JwtUtil;
import com.landminesoft.lms.entity.ResetToken;
import com.landminesoft.lms.entity.Student;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional

@Service
public class AuthServiceImpl implements AuthService {

    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;
    private final AdminRepository adminRepository;
    private final ResetTokenRepository resetTokenRepository; // ✅ NEW
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(StudentRepository studentRepository,
                           FacultyRepository facultyRepository,
                           AdminRepository adminRepository,
                           ResetTokenRepository resetTokenRepository, // ✅ NEW
                           BCryptPasswordEncoder passwordEncoder,
                           JwtUtil jwtUtil) {

        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
        this.adminRepository = adminRepository;
        this.resetTokenRepository = resetTokenRepository; // ✅ NEW
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // ================= REGISTER =================

    @Override
    public RegistrationResponseDTO registerStudent(StudentRegisterDTO dto) {
        if (studentRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        if (studentRepository.existsByRollNumber(dto.getRollNumber())) {
            throw new RuntimeException("Roll number already exists");
        }

        Student student = new Student();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setPhone(dto.getPhone());
        student.setRollNumber(dto.getRollNumber());
        student.setBranch(dto.getBranch());
        student.setSemester(dto.getSemester());
        student.setEnrollmentYear(dto.getEnrollmentYear());
        student.setPasswordHash(passwordEncoder.encode(dto.getPassword()));

        Student saved = studentRepository.save(student);

        return new RegistrationResponseDTO(
                saved.getId(),
                saved.getName(),
                saved.getEmail(),
                "STUDENT",
                "Registration successful. Please login."
        );
    }

    @Override
    public RegistrationResponseDTO registerFaculty(FacultyRegisterDTO dto) {
        if (facultyRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        FacultyPersonal faculty = new FacultyPersonal();
        faculty.setName(dto.getName());
        faculty.setEmail(dto.getEmail());
        faculty.setPhone(dto.getPhone());
        faculty.setDepartment(dto.getDepartment());
        faculty.setDesignation(dto.getDesignation());
        faculty.setPasswordHash(passwordEncoder.encode(dto.getPassword()));

        FacultyPersonal saved = facultyRepository.save(faculty);

        return new RegistrationResponseDTO(
                saved.getId(),
                saved.getName(),
                saved.getEmail(),
                "FACULTY",
                "Registration successful. Please login."
        );
    }

    @Override
    public RegistrationResponseDTO registerAdmin(AdminRegisterDTO dto) {
        if (adminRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        Admin admin = new Admin();
        admin.setName(dto.getName());
        admin.setEmail(dto.getEmail());
        admin.setPhone(dto.getPhone());
        admin.setRole(Admin.Role.valueOf(dto.getRole()));
        admin.setPasswordHash(passwordEncoder.encode(dto.getPassword()));

        Admin saved = adminRepository.save(admin);

        return new RegistrationResponseDTO(
                saved.getId(),
                saved.getName(),
                saved.getEmail(),
                "ADMIN",
                "Registration successful. Please login."
        );
    }

    // ================= LOGIN =================

    @Override
    public LoginResponseDTO loginStudent(LoginDTO dto) {
        Student student = studentRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(dto.getPassword(), student.getPasswordHash())) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(
                student.getId(),
                student.getEmail(),
                "STUDENT"
        );

        return new LoginResponseDTO(
                token, "Bearer",
                student.getId(),
                student.getEmail(),
                student.getName(),
                "STUDENT"
        );
    }

    @Override
    public LoginResponseDTO loginFaculty(LoginDTO dto) {
        FacultyPersonal faculty = facultyRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(dto.getPassword(), faculty.getPasswordHash())) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(
                faculty.getId(),
                faculty.getEmail(),
                "FACULTY"
        );

        return new LoginResponseDTO(
                token, "Bearer",
                faculty.getId(),
                faculty.getEmail(),
                faculty.getName(),
                "FACULTY"
        );
    }

    @Override
    public LoginResponseDTO loginAdmin(LoginDTO dto) {
        Admin admin = adminRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(dto.getPassword(), admin.getPasswordHash())) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(
                admin.getId(),
                admin.getEmail(),
                "ADMIN"
        );

        return new LoginResponseDTO(
                token, "Bearer",
                admin.getId(),
                admin.getEmail(),
                admin.getName(),
                "ADMIN"
        );
    }

    // ================= FORGOT PASSWORD =================

    @Override
    public void forgotPassword(ForgotPasswordDTO dto) {

        String email = dto.getEmail();

        boolean exists =
                studentRepository.existsByEmail(email) ||
                        facultyRepository.existsByEmail(email) ||
                        adminRepository.existsByEmail(email);

        if (!exists) {
            throw new RuntimeException("Email not registered");
        }

        // delete old token
        resetTokenRepository.deleteByEmail(email);

        String token = java.util.UUID.randomUUID().toString();

        ResetToken resetToken = new ResetToken();
        resetToken.setEmail(email);
        resetToken.setToken(token);
        resetToken.setExpiryDate(
                java.time.LocalDateTime.now().plusMinutes(30)
        );

        resetTokenRepository.save(resetToken);

        // temporary (until email integration)
        System.out.println("RESET TOKEN: " + token);
    }
    @Override
    public void resetPassword(ResetPasswordDTO dto) {

        // 1. Find token
        ResetToken resetToken = resetTokenRepository.findByToken(dto.getToken())
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        // 2. Check expiry
        if (resetToken.getExpiryDate().isBefore(java.time.LocalDateTime.now())) {
            throw new RuntimeException("Token expired");
        }

        String email = resetToken.getEmail();

        // 3. Update password (check all user types)
        if (studentRepository.existsByEmail(email)) {

            Student student = studentRepository.findByEmail(email).get();
            student.setPasswordHash(passwordEncoder.encode(dto.getNewPassword()));
            studentRepository.save(student);

        } else if (facultyRepository.existsByEmail(email)) {

            FacultyPersonal faculty = facultyRepository.findByEmail(email).get();
            faculty.setPasswordHash(passwordEncoder.encode(dto.getNewPassword()));
            facultyRepository.save(faculty);

        } else if (adminRepository.existsByEmail(email)) {

            Admin admin = adminRepository.findByEmail(email).get();
            admin.setPasswordHash(passwordEncoder.encode(dto.getNewPassword()));
            adminRepository.save(admin);
        }

        // 4. Delete token after use
        resetTokenRepository.deleteByEmail(email);
    }
    @Override
    public Student getStudentProfile(String email) {

        return studentRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }
    @Override
    public Student updateStudentProfile(String email, UpdateStudentDTO dto) {

        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        if (dto.getPhone() != null) {
            student.setPhone(dto.getPhone());
        }

        // only if your entity has address field
        if (dto.getAddress() != null) {
            student.setAddress(dto.getAddress());
        }

        return studentRepository.save(student);
    }
    @Override
    public void changePassword(String email, ChangePasswordDTO dto) {

        // find user (student for now)
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // check old password
        if (!passwordEncoder.matches(dto.getOldPassword(), student.getPasswordHash())) {
            throw new RuntimeException("Old password is incorrect");
        }

        // set new password
        student.setPasswordHash(passwordEncoder.encode(dto.getNewPassword()));

        studentRepository.save(student);
    }
}