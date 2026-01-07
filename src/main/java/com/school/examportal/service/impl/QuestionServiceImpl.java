package com.school.examportal.service.impl;

import com.school.examportal.dto.*;
import com.school.examportal.entity.*;
import com.school.examportal.repository.*;
import com.school.examportal.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final TestRepository testRepo;
    private final QuestionRepository questionRepo;
    private final TestAttemptRepository attemptRepo;


    @Override
    public void addQuestion(QuestionCreateRequest req) {

        Test test = testRepo.findById(req.getTestId())
                .orElseThrow(() -> new RuntimeException("Test not found"));

        if (test.getStatus() != TestStatus.DRAFT) {
            throw new RuntimeException("Cannot edit LIVE/CLOSED test");
        }

        Question q = new Question();
        q.setText(req.getText());
        q.setMarks(req.getMarks());
        q.setTest(test);

        List<Option> opts = new ArrayList<>();
        for (OptionRequest o : req.getOptions()) {
            Option opt = new Option();
            opt.setText(o.getText());
            opt.setCorrect(o.isCorrect());
            opt.setPosition(o.getPosition());
            opt.setQuestion(q);
            opts.add(opt);
        }

        q.setOptions(opts);
        questionRepo.save(q);
    }

    @Override
    public List<Question> getQuestions(Long testId) {
        Test test = testRepo.findById(testId)
                .orElseThrow(() -> new RuntimeException("Test not found"));
        return questionRepo.findByTestOrderByIdAsc(test);
    }
    @Override
    public List<Question> getQuestionsByAttempt(Long attemptId) {

        TestAttempt attempt = attemptRepo.findById(attemptId)
                .orElseThrow(() -> new RuntimeException("Attempt not found"));

        Test test = attempt.getTest();

        return questionRepo.findByTestOrderByIdAsc(test);
    }

}
