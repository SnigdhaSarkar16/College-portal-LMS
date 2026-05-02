package com.landminesoft.lms.service.fee;

import com.landminesoft.lms.entity.FeePayment;
import com.landminesoft.lms.entity.FeeStructure;

import java.math.BigDecimal;
import java.util.List;

public interface FeeService {

    FeeStructure createFeeStructure(FeeStructure feeStructure);

    FeePayment payFee(Long studentId, BigDecimal amount);

    List<FeePayment> getStudentPayments(Long studentId);
}