package com.school.examportal.service;

import com.school.examportal.entity.TestAttempt;

import java.util.List;

public interface ResultService {
    List<TestAttempt> studentResults(String rollNo);
}
