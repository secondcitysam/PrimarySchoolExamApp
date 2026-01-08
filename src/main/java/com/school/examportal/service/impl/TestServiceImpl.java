package com.school.examportal.service.impl;

import com.school.examportal.dto.TestCreateRequest;
import com.school.examportal.entity.Student;
import com.school.examportal.entity.Test;
import com.school.examportal.entity.TestStatus;
import com.school.examportal.entity.Topic;
import com.school.examportal.repository.QuestionRepository;
import com.school.examportal.repository.StudentRepository;
import com.school.examportal.repository.TestRepository;
import com.school.examportal.repository.TopicRepository;
import com.school.examportal.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final TestRepository testRepo;
    private final TopicRepository topicRepo;
    private final StudentRepository studentRepo;
    private final QuestionRepository questionRepo;
    @Override
    public Test findById(Long id) {
        return testRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Test not found"));
    }

    @Override
    public void create(TestCreateRequest req) {
        Topic topic = topicRepo.findById(req.getTopicId())
                .orElseThrow(() -> new RuntimeException("Topic not found"));

        Test t = new Test();
        t.setTitle(req.getTitle());
        t.setStandard(req.getStandard());
        t.setDurationMinutes(req.getDurationMinutes());
        t.setTotalMarks(req.getTotalMarks());
        t.setTopic(topic);
        t.setStatus(TestStatus.DRAFT);

        testRepo.save(t);
    }

    @Override
    public void makeLive(Long testId) {

        Test test = testRepo.findById(testId)
                .orElseThrow(() -> new RuntimeException("Test not found"));

        if (test.getStatus() != TestStatus.DRAFT) {
            throw new RuntimeException("Only DRAFT tests can go LIVE");
        }

        // 🔴 VALIDATION STARTS HERE
        Integer questionMarks =
                questionRepo.sumMarksByTestId(testId);

        if (!questionMarks.equals(test.getTotalMarks())) {
            throw new RuntimeException(
                    "Total question marks (" + questionMarks +
                            ") do not match test total marks (" +
                            test.getTotalMarks() + ")"
            );
        }

        test.setStatus(TestStatus.LIVE);
        testRepo.save(test);
    }

    @Override
    public List<Test> studentVisible(int standard) {
        return testRepo.findByStatusAndStandard(TestStatus.LIVE, standard);
    }

    @Override
    public int getStudentStandard(String rollNo) {

        Student student = studentRepo.findByRollNo(rollNo)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return student.getStandard();
    }

    @Override
    public List<Test> findByTopic(Long topicId) {
        return testRepo.findByTopicId(topicId);
    }

}
