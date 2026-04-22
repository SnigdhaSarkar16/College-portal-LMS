package com.landminesoft.lms.service.auth;

import com.landminesoft.lms.dto.request.*;
import com.landminesoft.lms.dto.response.RegistrationResponseDTO;
import com.landminesoft.lms.dto.response.LoginResponseDTO;
import com.landminesoft.lms.entity.*;
import com.landminesoft.lms.repository.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;
    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthServiceImpl(StudentRepository studentRepository,
                           FacultyRepository facultyRepository,
                           AdminRepository adminRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // ================= STUDENT REGISTER =================

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

    // ================= FACULTY REGISTER =================

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

    // ================= ADMIN REGISTER =================

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

    // ================= STUDENT LOGIN =================

    @Override
    public LoginResponseDTO loginStudent(LoginDTO dto) {

        Student student = studentRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(dto.getPassword(), student.getPasswordHash())) {
            throw new RuntimeException("Invalid email or password");
        }

        return new LoginResponseDTO(
                "dummy-token",
                "Bearer",
                student.getId(),
                student.getEmail(),
                student.getName(),
                "STUDENT"
        );
    }

    // ================= FACULTY LOGIN =================

    @Override
    public LoginResponseDTO loginFaculty(LoginDTO dto) {

        FacultyPersonal faculty = facultyRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(dto.getPassword(), faculty.getPasswordHash())) {
            throw new RuntimeException("Invalid email or password");
        }

        return new LoginResponseDTO(
                "dummy-token",
                "Bearer",
                faculty.getId(),
                faculty.getEmail(),
                faculty.getName(),
                "FACULTY"
        );
    }

    // ================= ADMIN LOGIN =================

    @Override
    public LoginResponseDTO loginAdmin(LoginDTO dto) {

        Admin admin = adminRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(dto.getPassword(), admin.getPasswordHash())) {
            throw new RuntimeException("Invalid email or password");
        }

        return new LoginResponseDTO(
                "dummy-token",
                "Bearer",
                admin.getId(),
                admin.getEmail(),
                admin.getName(),
                "ADMIN"
        );
    }
}