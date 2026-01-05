package com.school.examportal.controller.rest;

import com.school.examportal.dto.StudentLoginRequest;
import com.school.examportal.dto.TeacherLoginRequest;
import com.school.examportal.dto.TeacherSignupRequest;
import com.school.examportal.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthRestController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody TeacherSignupRequest request) {
        authService.signup(request);
        return ResponseEntity.ok("Signup successful");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @Valid @RequestBody TeacherLoginRequest request,
            HttpServletResponse response
    ) {
        String token = authService.login(request);

        Cookie cookie = new Cookie("JWT", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(24 * 60 * 60);

        response.addCookie(cookie);

        return ResponseEntity.ok("Login successful");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {

        Cookie cookie = new Cookie("JWT", null);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0); // delete

        response.addCookie(cookie);

        return ResponseEntity.ok("Logged out");
    }

    @PostMapping("/student-login")
    public ResponseEntity<?> studentLogin(
            @RequestBody StudentLoginRequest request,
            HttpServletResponse response
    ) {
        String token = authService.studentLogin(
                request.getRollNo(),
                request.getPassword()
        );

        Cookie cookie = new Cookie("JWT", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(24 * 60 * 60);

        response.addCookie(cookie);

        return ResponseEntity.ok("Student login successful");
    }



}
