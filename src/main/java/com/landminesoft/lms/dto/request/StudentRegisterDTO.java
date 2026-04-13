package com.landminesoft.lms.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class StudentRegisterDTO {

    @NotBlank(message = "Name is required")
    @Size(max = 100)
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 100)
    private String email;

    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "^[0-9]{10,15}$", message = "Invalid phone number")
    private String phone;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=!]).*$",
            message = "Password must contain uppercase, number, and special character"
    )
    private String password;

    @NotBlank(message = "Branch is required")
    @Pattern(regexp = "CSE|ECE|ME|CE|EE", message = "Invalid branch")
    private String branch;

    @NotNull(message = "Enrollment year is required")
    @Min(value = 2000)
    @Max(value = 2100)
    private Integer enrollmentYear;
}