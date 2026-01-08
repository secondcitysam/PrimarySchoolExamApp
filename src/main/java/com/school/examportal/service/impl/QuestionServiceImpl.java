package com.school.examportal.service.impl;

import com.school.examportal.dto.QuestionCreateRequest;
import com.school.examportal.dto.QuestionUpsertRequest;
import com.school.examportal.entity.*;
import com.school.examportal.repository.OptionRepository;
import com.school.examportal.repository.QuestionRepository;
import com.school.examportal.repository.TestAttemptRepository;
import com.school.examportal.repository.TestRepository;
import com.school.examportal.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final TestRepository testRepo;
    private final QuestionRepository questionRepo;
    private final TestAttemptRepository attemptRepo;
    private final OptionRepository optionRepo;

    private void ensureEditable(Test test) {
        if (test.getStatus() == TestStatus.LIVE) {
            throw new RuntimeException("Cannot modify questions of LIVE test");
        }
    }


    @Override
    public void add(Long testId, QuestionCreateRequest req) {

        Test test = testRepo.findById(testId)
                .orElseThrow(() -> new RuntimeException("Test not found"));

        ensureEditable(test);
        Question q = new Question();
        q.setText(req.getText());
        q.setMarks(req.getMarks());   // ✅ IMPORTANT
        q.setTest(test);

        questionRepo.save(q);

        for (int i = 0; i < 4; i++) {
            Option o = new Option();
            o.setText(req.getOptions().get(i));
            o.setCorrect(i == req.getCorrectIndex());
            o.setPosition(i);
            o.setQuestion(q);
            optionRepo.save(o);
        }
    }

    @Override
    public void upsert(Long testId, QuestionUpsertRequest req) {

        Test test = testRepo.findById(testId)
                .orElseThrow();

        ensureEditable(test);

        Question q;

        if (req.getQuestionId() == null) {
            q = new Question();
            q.setTest(test);
        } else {
            q = questionRepo.findById(req.getQuestionId())
                    .orElseThrow();
        }

        q.setText(req.getText());
        q.setMarks(req.getMarks());

        questionRepo.save(q);

        // Clear old options
        optionRepo.deleteByQuestion(q);

        for (int i = 0; i < 4; i++) {
            Option o = new Option();
            o.setText(req.getOptions().get(i));
            o.setCorrect(i == req.getCorrectIndex());
            o.setPosition(i);
            o.setQuestion(q);
            optionRepo.save(o);
        }
    }

    @Override
    public void moveOption(Long questionId, int from, int to) {

        Question q = questionRepo.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        ensureEditable(q.getTest());

        List<Option> options =
                optionRepo.findByQuestionOrderByPositionAsc(q);

        // bounds safety (VERY IMPORTANT)
        if (to < 0 || to >= options.size()) return;

        Collections.swap(options, from, to);

        for (int i = 0; i < options.size(); i++) {
            options.get(i).setPosition(i);
        }

        optionRepo.saveAll(options);
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
