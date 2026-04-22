package com.landminesoft.lms.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDTO {

    private String token;
    private String type;
    private Long userId;
    private String email;
    private String name;
    private String role;
}
