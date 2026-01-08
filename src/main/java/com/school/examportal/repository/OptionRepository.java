package com.school.examportal.repository;

import com.school.examportal.entity.Option;
import com.school.examportal.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionRepository extends JpaRepository<Option, Long> {

    List<Option> findByQuestionOrderByPositionAsc(Question question);

    void deleteByQuestion(Question question);
}
