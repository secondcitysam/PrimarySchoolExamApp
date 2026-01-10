package com.school.examportal.controller.page;

import com.school.examportal.entity.Question;
import com.school.examportal.entity.TestStatus;
import com.school.examportal.service.QuestionService;
import com.school.examportal.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TeacherQuestionPreviewController {

    private final QuestionService questionService;
    private final TestService testService;

    @GetMapping("/teacher/tests/{testId}/questions/preview")
    public String preview(
            @PathVariable Long testId,
            @RequestParam(defaultValue = "0") int index,
            Model model
    ) {
        List<Question> questions =
                questionService.getQuestions(testId);

        if (questions.isEmpty()) {
            model.addAttribute("empty", true);
            return "preview-questions";
        }

        if (index < 0) index = 0;
        if (index >= questions.size()) index = questions.size() - 1;

        model.addAttribute("question", questions.get(index));
        model.addAttribute("index", index);
        model.addAttribute("total", questions.size());
        model.addAttribute("testId", testId);

        boolean locked =
                testService.findById(testId).getStatus() == TestStatus.LIVE;

        model.addAttribute("locked", locked);

        return "preview-questions";
    }

}
