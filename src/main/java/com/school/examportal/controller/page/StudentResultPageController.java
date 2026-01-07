package com.school.examportal.controller.page;

import com.school.examportal.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class StudentResultPageController {

    private final ResultService resultService;

    @GetMapping("/student/results")
    public String results(Authentication auth, Model model) {

        model.addAttribute("results",
                resultService.studentResults(auth.getName()));

        return "student-results";
    }
}
