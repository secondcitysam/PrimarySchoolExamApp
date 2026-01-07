package com.school.examportal.service.impl;

import com.school.examportal.entity.Student;
import com.school.examportal.entity.TestAttempt;
import com.school.examportal.repository.StudentRepository;
import com.school.examportal.repository.TestAttemptRepository;
import com.school.examportal.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {

    private final StudentRepository studentRepo;
    private final TestAttemptRepository attemptRepo;

    @Override
    public List<TestAttempt> studentResults(String rollNo) {

        Student student = studentRepo.findByRollNo(rollNo)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return attemptRepo.findByStudent(student);
    }
}
