package com.school.examportal.service.impl;

import com.school.examportal.entity.*;
import com.school.examportal.repository.StudentRepository;
import com.school.examportal.repository.TestAttemptRepository;
import com.school.examportal.repository.TestRepository;
import com.school.examportal.service.TestAttemptService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TestAttemptServiceImpl implements TestAttemptService {

    private final TestRepository testRepo;
    private final StudentRepository studentRepo;
    private final TestAttemptRepository attemptRepo;
    private final RedisTemplate<String, Object> redis;

    @Override
    public Long startTest(Long testId, String rollNo) {

        Student student = studentRepo.findByRollNo(rollNo)
                .orElseThrow();

        Test test = testRepo.findById(testId)
                .orElseThrow();

        if (test.getStatus() != TestStatus.LIVE) {
            throw new RuntimeException("Test not live");
        }

        if (attemptRepo.existsByStudentAndTest(student, test)) {
            throw new RuntimeException("Already attempted");
        }

        TestAttempt attempt = new TestAttempt();
        attempt.setStudent(student);
        attempt.setTest(test);
        attempt.setStartedAt(LocalDateTime.now());
        attempt.setSubmitted(false);

        attemptRepo.save(attempt);

        String key = "test:" + testId + ":student:" + student.getId();
        redis.opsForValue().set(
                key,
                attempt.getId(),
                Duration.ofMinutes(test.getDurationMinutes())
        );

        return attempt.getId();
    }

    @Override
    public void submitTest(Long attemptId) {

        TestAttempt attempt = attemptRepo.findById(attemptId)
                .orElseThrow(() -> new RuntimeException("Attempt not found"));

        if (attempt.isSubmitted()) return;

        int score = calculateScore(attempt);

        attempt.setScore(score);
        attempt.setSubmitted(true);
        attempt.setSubmittedAt(LocalDateTime.now());

        attemptRepo.save(attempt);
    }


    private int calculateScore(TestAttempt attempt) {

        int score = 0;

        for (Answer a : attempt.getAnswers()) {
            if (a.getSelectedOption().isCorrect()) {
                score += a.getQuestion().getMarks();
            }
        }
        return score;
    }

}
