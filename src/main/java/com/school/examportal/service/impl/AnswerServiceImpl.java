package com.school.examportal.service.impl;

import com.school.examportal.dto.AnswerRequest;
import com.school.examportal.entity.*;
import com.school.examportal.repository.*;
import com.school.examportal.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final TestAttemptRepository attemptRepo;
    private final QuestionRepository questionRepo;
    private final OptionRepository optionRepo;
    private final AnswerRepository answerRepo;

    @Override
    public void save(AnswerRequest req) {

        TestAttempt attempt = attemptRepo.findById(req.getAttemptId())
                .orElseThrow(() -> new RuntimeException("Attempt not found"));

        if (attempt.isSubmitted()) {
            throw new RuntimeException("Attempt already submitted");
        }

        Question question = questionRepo.findById(req.getQuestionId())
                .orElseThrow(() -> new RuntimeException("Question not found"));

        Option option = optionRepo.findById(req.getOptionId())
                .orElseThrow(() -> new RuntimeException("Option not found"));

        Answer answer = answerRepo
                .findByAttemptAndQuestion(attempt, question)
                .orElse(new Answer());

        answer.setAttempt(attempt);
        answer.setQuestion(question);
        answer.setSelectedOption(option);

        answerRepo.save(answer);
    }
}
