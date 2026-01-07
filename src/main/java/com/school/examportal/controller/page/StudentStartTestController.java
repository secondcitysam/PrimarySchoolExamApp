package com.school.examportal.controller.page;

import com.school.examportal.service.TestAttemptService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class StudentStartTestController {

    private final TestAttemptService attemptService;

    @GetMapping("/student/tests/{id}/start")
    public String start(
            @PathVariable Long id,
            Authentication auth
    ) {
        String rollNo = auth.getName();

        Long attemptId = attemptService.startTest(id, rollNo);

        return "redirect:/student/attempts/" + attemptId;
    }
}
