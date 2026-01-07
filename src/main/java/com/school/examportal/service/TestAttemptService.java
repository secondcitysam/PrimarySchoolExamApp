package com.school.examportal.service;

public interface TestAttemptService {
    Long startTest(Long testId, String rollNo);
    void submitTest(Long attemptId);


}
