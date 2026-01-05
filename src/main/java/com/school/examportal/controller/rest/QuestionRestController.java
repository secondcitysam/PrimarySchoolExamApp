package com.school.examportal.controller.rest;

import com.school.examportal.dto.QuestionCreateRequest;
import com.school.examportal.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teacher/questions")
@RequiredArgsConstructor
public class QuestionRestController {

    private final QuestionService service;

    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody QuestionCreateRequest req) {
        service.addQuestion(req);
        return ResponseEntity.ok("Question added");
    }
}
