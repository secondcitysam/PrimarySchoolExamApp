package com.school.examportal.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherLoginRequest {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;
}
