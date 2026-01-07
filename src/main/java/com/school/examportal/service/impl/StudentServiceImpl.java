package com.school.examportal.service.impl;

import com.school.examportal.dto.StudentCreateRequest;
import com.school.examportal.entity.Student;
import com.school.examportal.repository.StudentRepository;
import com.school.examportal.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void createStudent(StudentCreateRequest request) {

        if (studentRepository.findByRollNo(request.getRollNo()).isPresent()) {
            throw new RuntimeException("Roll number already exists");
        }

        Student student = new Student();
        student.setRollNo(request.getRollNo());
        student.setPassword(passwordEncoder.encode(request.getPassword()));
        student.setName(request.getName());
        student.setStandard(request.getStandard());
        student.setRole("STUDENT");

        studentRepository.save(student);
    }


}
