package com.school.examportal.repository;

import com.school.examportal.entity.Test;
import com.school.examportal.entity.TestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestRepository extends JpaRepository<Test, Long> {

    List<Test> findByStatusAndStandard(TestStatus status, int standard);
}
