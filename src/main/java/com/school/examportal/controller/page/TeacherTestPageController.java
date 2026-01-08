package com.school.examportal.controller.page;

import com.school.examportal.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class TeacherTestPageController {

    private final TestService testService;

    @GetMapping("/teacher/topics/{topicId}/tests")
    public String tests(@PathVariable Long topicId, Model model) {

        model.addAttribute("tests",
                testService.findByTopic(topicId));

        model.addAttribute("topicId", topicId);

        return "tests";
    }

    @GetMapping("/teacher/topics/{topicId}/tests/add")
    public String addTest(@PathVariable Long topicId, Model model) {

        model.addAttribute("topicId", topicId);
        return "add-test";
    }
}
