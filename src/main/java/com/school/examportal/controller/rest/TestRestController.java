package com.school.examportal.controller.rest;

import com.school.examportal.dto.TestCreateRequest;
import com.school.examportal.service.TestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teacher/tests")
@RequiredArgsConstructor
public class TestRestController {

    private final TestService service;

    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody TestCreateRequest req) {
        service.create(req);
        return ResponseEntity.ok("Test created");
    }

    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping("/{id}/live")
    public ResponseEntity<?> live(@PathVariable Long id) {
        service.makeLive(id);
        return ResponseEntity.ok("Test is LIVE");
    }
}
