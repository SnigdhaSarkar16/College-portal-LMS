package com.landminesoft.lms.controller.fee;

import com.landminesoft.lms.entity.FeePayment;
import com.landminesoft.lms.entity.FeeStructure;
import com.landminesoft.lms.entity.Student;
import com.landminesoft.lms.repository.StudentRepository;
import com.landminesoft.lms.service.fee.FeeService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FeeController {

    private final FeeService feeService;
    private final StudentRepository studentRepository;

    public FeeController(FeeService feeService,
                         StudentRepository studentRepository) {
        this.feeService = feeService;
        this.studentRepository = studentRepository;
    }

    // 🔐 ADMIN creates fee structure
    @PostMapping("/admin/fees")
    public ResponseEntity<FeeStructure> createFee(@RequestBody FeeStructure feeStructure) {
        return ResponseEntity.ok(feeService.createFeeStructure(feeStructure));
    }

    // 🔐 STUDENT pays fee
    @PostMapping("/student/pay-fee")
    public ResponseEntity<FeePayment> payFee(
            @RequestParam BigDecimal amount,
            Authentication authentication
    ) {
        String email = authentication.getName();

        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return ResponseEntity.ok(
                feeService.payFee(student.getId(), amount)
        );
    }

    // 🔐 STUDENT views payments
    @GetMapping("/student/fees")
    public ResponseEntity<List<FeePayment>> getFees(Authentication authentication) {

        String email = authentication.getName();

        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return ResponseEntity.ok(
                feeService.getStudentPayments(student.getId())
        );
    }
}