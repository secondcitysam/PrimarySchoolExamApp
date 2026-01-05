package com.school.examportal.controller.rest;

import com.school.examportal.dto.TopicCreateRequest;
import com.school.examportal.service.TopicService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teacher/topics")
@RequiredArgsConstructor
public class TopicRestController {

    private final TopicService service;

    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody TopicCreateRequest req) {
        service.create(req);
        return ResponseEntity.ok("Topic created");
    }
}
