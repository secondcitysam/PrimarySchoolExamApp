package com.school.examportal.repository;

import com.school.examportal.entity.Question;
import com.school.examportal.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByTestOrderByIdAsc(Test test);

    @Query("""
        select coalesce(sum(q.marks), 0)
        from Question q
        where q.test.id = :testId
    """)
    Integer sumMarksByTestId(@Param("testId") Long testId);
}
