package com.school.examportal.controller.rest;

import com.school.examportal.dto.StudentCreateRequest;
import com.school.examportal.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teacher/students")
@RequiredArgsConstructor
public class StudentRestController {

    private final StudentService studentService;

    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping
    public ResponseEntity<?> createStudent(
            @Valid @RequestBody StudentCreateRequest request
    ) {
        studentService.createStudent(request);
        return ResponseEntity.ok("Student created");
    }
}
