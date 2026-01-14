package com.school.examportal.controller.page;

import com.school.examportal.service.TestAttemptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class StudentHistoryPageController {

    private final TestAttemptService attemptService;

    @GetMapping("/student/history")
    public String history(Model model, Principal principal) {

        model.addAttribute(
                "attempts",
                attemptService.findAttemptsByStudent(principal.getName())
        );

        return "student-history";
    }
}
