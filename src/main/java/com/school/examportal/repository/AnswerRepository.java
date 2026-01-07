package com.school.examportal.repository;

import com.school.examportal.entity.Answer;
import com.school.examportal.entity.Question;
import com.school.examportal.entity.TestAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    Optional<Answer> findByAttemptAndQuestion(TestAttempt attempt, Question question);
}
