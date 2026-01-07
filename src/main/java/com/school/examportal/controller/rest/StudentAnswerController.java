package com.school.examportal.controller.rest;

import com.school.examportal.service.TestAttemptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentAnswerController {

    private final AnswerService answerService;
    private final TestAttemptService attemptService;

    @PostMapping("/answers")
    public ResponseEntity<?> save(@RequestBody AnswerRequest req) {
        answerService.save(req);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/attempts/{id}/submit")
    public ResponseEntity<?> submit(@PathVariable Long id) {
        attemptService.submitTest(id);
        return ResponseEntity.ok().build();
    }
}
