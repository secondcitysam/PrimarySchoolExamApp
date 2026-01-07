package com.school.examportal.service;

import com.school.examportal.entity.TestAttempt;

import java.util.List;

public interface TeacherAnalyticsService {
    List<TestAttempt> attemptsForTest(Long testId);
}
