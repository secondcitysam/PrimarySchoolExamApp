package com.school.examportal.service;

import com.school.examportal.entity.TestAttempt;

import java.util.List;

public interface TestAttemptService {
    Long startTest(Long testId, String rollNo);
    void submitTest(Long attemptId);
    List<TestAttempt> findAttemptsByStudent(String rollNo);


}
