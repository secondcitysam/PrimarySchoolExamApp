package com.school.examportal.controller.page;

import com.school.examportal.entity.Test;
import com.school.examportal.service.TestAttemptService;
import com.school.examportal.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class StudentTestPageController {

    private final TestService testService;
    private final TestAttemptService attemptService;

    /**
     * Student Dashboard / Available Tests
     */
    @GetMapping("/student/tests")
    public String availableTests(Principal principal, Model model) {

        int standard =
                testService.getStudentStandard(principal.getName());

        List<Test> tests =
                testService.studentVisible(standard);

        model.addAttribute("tests", tests);
        return "student-tests";
    }

    /**
     * Start Test → creates attempt + Redis TTL
     */
    @PostMapping("/student/tests/{testId}/start")
    public String startTest(
            @PathVariable Long testId,
            Principal principal
    ) {
        Long attemptId =
                attemptService.startTest(
                        testId,
                        principal.getName()
                );

        return "redirect:/student/attempts/" + attemptId;
    }

}
