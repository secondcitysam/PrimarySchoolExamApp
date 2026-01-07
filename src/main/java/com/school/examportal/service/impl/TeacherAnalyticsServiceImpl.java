package com.school.examportal.service.impl;

import com.school.examportal.entity.Test;
import com.school.examportal.entity.TestAttempt;
import com.school.examportal.repository.TestAttemptRepository;
import com.school.examportal.repository.TestRepository;
import com.school.examportal.service.TeacherAnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherAnalyticsServiceImpl
        implements TeacherAnalyticsService {

    private final TestRepository testRepo;
    private final TestAttemptRepository attemptRepo;

    @Override
    public List<TestAttempt> attemptsForTest(Long testId) {

        Test test = testRepo.findById(testId)
                .orElseThrow(() -> new RuntimeException("Test not found"));

        return attemptRepo.findByTest(test);
    }
}
