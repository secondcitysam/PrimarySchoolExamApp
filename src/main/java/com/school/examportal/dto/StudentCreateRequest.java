package com.school.examportal.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentCreateRequest {

    @NotBlank
    private String rollNo;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @Min(1)
    @Max(4)
    private int standard;
}
