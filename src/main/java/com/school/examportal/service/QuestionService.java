package com.school.examportal.service;

import com.school.examportal.dto.QuestionCreateRequest;
import com.school.examportal.entity.Question;
import java.util.List;

public interface QuestionService {

    void addQuestion(QuestionCreateRequest req);

    List<Question> getQuestions(Long testId);

    List<Question> getQuestionsByAttempt(Long attemptId); // ✅ ADD
}
