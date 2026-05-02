package com.landminesoft.lms.service.fee;

import com.landminesoft.lms.entity.*;
import com.landminesoft.lms.exception.UnauthorizedException;
import com.landminesoft.lms.repository.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class FeeServiceImpl implements FeeService {

    private final FeeStructureRepository feeStructureRepository;
    private final FeePaymentRepository feePaymentRepository;
    private final StudentRepository studentRepository;

    public FeeServiceImpl(FeeStructureRepository feeStructureRepository,
                          FeePaymentRepository feePaymentRepository,
                          StudentRepository studentRepository) {
        this.feeStructureRepository = feeStructureRepository;
        this.feePaymentRepository = feePaymentRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public FeeStructure createFeeStructure(FeeStructure feeStructure) {
        return feeStructureRepository.save(feeStructure);
    }

    @Override
    public FeePayment payFee(Long studentId, BigDecimal amount) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new UnauthorizedException("Student not found"));

        // get fee structure based on student
        FeeStructure feeStructure = feeStructureRepository
                .findByBranchAndSemester(student.getBranch(), student.getSemester())
                .orElseThrow(() -> new RuntimeException("Fee structure not found"));

        FeePayment payment = new FeePayment();
        payment.setStudent(student);
        payment.setFeeStructure(feeStructure);
        payment.setAmountPaid(amount);
        payment.setPaymentStatus("COMPLETED");

        payment.setTransactionId(UUID.randomUUID().toString());
        payment.setReceiptNumber("RCPT-" + System.currentTimeMillis());

        return feePaymentRepository.save(payment);
    }

    @Override
    public List<FeePayment> getStudentPayments(Long studentId) {
        return feePaymentRepository.findByStudentId(studentId);
    }
}