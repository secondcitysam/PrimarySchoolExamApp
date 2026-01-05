package com.school.examportal.controller.page;

import com.school.examportal.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class TopicPageController {

    private final TopicService service;

    @GetMapping("/teacher/topics")
    public String topics(Model model) {
        model.addAttribute("topics", service.findAll());
        return "topics";
    }

    @GetMapping("/teacher/topics/add")
    public String addTopic() {
        return "add-topic";
    }
}
