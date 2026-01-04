package com.school.examportal.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentPageController {

    @GetMapping("/student/login")
    public String studentLogin() {
        return "student-login";
    }

    @GetMapping("/student/home")
    public String studentHome() {
        return "student-home";
    }
}
