package com.school.examportal.controller.rest;

import com.school.examportal.dto.QuestionUpsertRequest;
import com.school.examportal.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/teacher/tests/{testId}/questions")
@RequiredArgsConstructor
public class QuestionRestController {

    private final QuestionService service;

    @PostMapping
    @PreAuthorize("hasRole('TEACHER')")
    public void upsert(
            @PathVariable Long testId,
            @RequestBody QuestionUpsertRequest req
    ) {
        service.upsert(testId, req);
    }

    @PostMapping("/{questionId}/options/move")
    @PreAuthorize("hasRole('TEACHER')")
    public void moveOption(
            @PathVariable Long questionId,
            @RequestParam int from,
            @RequestParam int to
    ) {
        service.moveOption(questionId, from, to);
    }
}
