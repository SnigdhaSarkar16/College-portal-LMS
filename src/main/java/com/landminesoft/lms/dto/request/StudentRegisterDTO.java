package com.landminesoft.lms.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class StudentRegisterDTO {

    @NotBlank(message = "Name is required")
    @Size(max = 100)
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "^[0-9]{10,15}$")
    private String phone;

    @NotBlank(message = "Password is required")
    @Size(min = 8)
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=!]).*$")
    private String password;

    @NotBlank(message = "Branch is required")
    private String branch;


    @NotNull
    private Integer enrollmentYear;

    @NotBlank(message = "Roll number is required")
    private String rollNumber;

    @NotNull(message = "Semester is required")
    @Min(1)
    @Max(8)
    private Integer semester;
}