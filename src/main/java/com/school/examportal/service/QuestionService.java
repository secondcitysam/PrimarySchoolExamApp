package com.school.examportal.service;

import com.school.examportal.dto.QuestionCreateRequest;
import com.school.examportal.dto.QuestionUpsertRequest;
import com.school.examportal.entity.Question;

import java.util.List;

public interface QuestionService {

    void add(Long testId, QuestionCreateRequest req);
    void upsert(Long testId, QuestionUpsertRequest req);
    void moveOption(Long questionId, int from, int to);

    List<Question> getQuestions(Long testId);

    List<Question> getQuestionsByAttempt(Long attemptId); // ✅ ADD
}
