package com.school.examportal.controller.page;

import com.school.examportal.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class QuestionPageController {

    private final QuestionService service;

    @GetMapping("/teacher/tests/{id}/questions")
    public String questions(@PathVariable Long id, Model model) {
        model.addAttribute("questions", service.getQuestions(id));
        model.addAttribute("testId", id);
        return "questions";
    }
}
