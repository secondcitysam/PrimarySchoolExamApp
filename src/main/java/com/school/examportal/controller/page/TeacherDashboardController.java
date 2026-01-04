package com.school.examportal.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class TeacherDashboardController {

    @PreAuthorize("hasRole('TEACHER')")
    @GetMapping("/teacher/dashboard")
    public String dashboard() {
        return "teacher-dashboard";
    }

    @PreAuthorize("hasRole('TEACHER')")
    @GetMapping("/teacher/students/add")
    public String addStudentPage() {
        return "add-student";
    }
}
