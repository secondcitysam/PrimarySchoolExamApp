package com.school.examportal.controller.page;

import com.school.examportal.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class StudentTestPageController {

    private final TestService service;

    @GetMapping("/student/tests")
    public String tests(Authentication auth, Model model) {
        // standard will come from Student later; for now assume stored
        int standard = 2; // TEMP (replace in Version 4)
        model.addAttribute("tests", service.studentVisible(standard));
        return "student-tests";
    }
}
