package com.school.examportal.repository;

import com.school.examportal.entity.Student;
import com.school.examportal.entity.Test;
import com.school.examportal.entity.TestAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TestAttemptRepository extends JpaRepository<TestAttempt, Long> {

    boolean existsByStudentAndTest(Student student, Test test);

    List<TestAttempt> findByStudent(Student student);

    List<TestAttempt> findByTest(Test test);
    List<TestAttempt> findByStudentOrderByStartedAtDesc(Student student);

    Optional<TestAttempt> findByStudentAndTest(Student student, Test test);
}

