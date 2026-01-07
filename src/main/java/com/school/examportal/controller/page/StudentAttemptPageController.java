package com.school.examportal.controller.page;

import com.school.examportal.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class StudentAttemptPageController {

    private final QuestionService questionService;

    @GetMapping("/student/attempts/{attemptId}")
    public String attempt(
            @PathVariable Long attemptId,
            Model model
    ) {
        model.addAttribute("attemptId", attemptId);
        model.addAttribute("questions",
                questionService.getQuestionsByAttempt(attemptId));
        return "student-attempt";
    }
}
