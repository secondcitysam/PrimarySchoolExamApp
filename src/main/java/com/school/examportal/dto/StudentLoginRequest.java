package com.school.examportal.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentLoginRequest {
    private String rollNo;
    private String password;
}
