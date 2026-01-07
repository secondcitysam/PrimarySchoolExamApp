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

    private final TestService testService;

    @GetMapping("/student/tests")
    public String tests(Authentication auth, Model model) {

        // auth.getName() == rollNo (JWT subject)
        String rollNo = auth.getName();

        // TEMP: get standard from DB
        // (you already have StudentRepository)
        int standard = testService.getStudentStandard(rollNo);

        model.addAttribute("tests",
                testService.studentVisible(standard));

        return "student-tests";
    }
}

