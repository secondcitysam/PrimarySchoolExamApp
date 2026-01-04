package com.school.examportal.service;

import com.school.examportal.dto.TeacherLoginRequest;
import com.school.examportal.dto.TeacherSignupRequest;

public interface AuthService {

    void signup(TeacherSignupRequest request);

    String login(TeacherLoginRequest request);

    String studentLogin(String rollNo, String password);

}
