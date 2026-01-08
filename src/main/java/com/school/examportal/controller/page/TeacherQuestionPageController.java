package com.school.examportal.controller.page;

import com.school.examportal.entity.Test;
import com.school.examportal.entity.TestStatus;
import com.school.examportal.service.QuestionService;
import com.school.examportal.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class TeacherQuestionPageController {

    private final QuestionService questionService;
    private final TestService testService;
    @GetMapping("/teacher/tests/{testId}/questions")
    public String questions(@PathVariable Long testId, Model model) {

        Test test = testService.findById(testId); // or repo call

        model.addAttribute("testId", testId);
        model.addAttribute("questions",
                questionService.getQuestions(testId));

        // 🔒 THIS LINE GOES HERE
        model.addAttribute("locked",
                test.getStatus() == TestStatus.LIVE);

        return "add-questions";
    }

}
