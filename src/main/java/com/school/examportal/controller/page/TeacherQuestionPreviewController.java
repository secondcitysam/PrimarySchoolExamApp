package com.school.examportal.controller.page;

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
public class TeacherQuestionPreviewController {

    private final QuestionService questionService;
    private final TestService testService;

    @GetMapping("/teacher/tests/{testId}/questions/preview")
    public String preview(
            @PathVariable Long testId,
            Model model
    ) {
        model.addAttribute("testId", testId);
        model.addAttribute(
                "questions",
                questionService.getQuestions(testId)
        );

        model.addAttribute(
                "locked",
                testService.findById(testId).getStatus() == TestStatus.LIVE
        );

        return "preview-questions";
    }
}
