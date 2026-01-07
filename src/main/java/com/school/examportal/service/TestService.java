package com.school.examportal.service;

import com.school.examportal.dto.TestCreateRequest;
import com.school.examportal.entity.Test;

import java.util.List;

public interface TestService {
    void create(TestCreateRequest req);
    void makeLive(Long testId);
    List<Test> studentVisible(int standard);

    int getStudentStandard(String rollNo);

}
