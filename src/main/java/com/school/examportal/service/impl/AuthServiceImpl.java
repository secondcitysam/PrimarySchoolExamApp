package com.school.examportal.service.impl;

import com.school.examportal.config.JwtUtil;
import com.school.examportal.dto.TeacherLoginRequest;
import com.school.examportal.dto.TeacherSignupRequest;
import com.school.examportal.entity.Student;
import com.school.examportal.entity.Teacher;
import com.school.examportal.repository.StudentRepository;
import com.school.examportal.repository.TeacherRepository;
import com.school.examportal.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final ModelMapper modelMapper;

    @Override
    public void signup(TeacherSignupRequest request) {

        if (teacherRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        Teacher teacher = modelMapper.map(request, Teacher.class);
        teacher.setPassword(passwordEncoder.encode(request.getPassword()));
        teacher.setRole("TEACHER");

        teacherRepository.save(teacher);
    }

    @Override
    public String login(TeacherLoginRequest request) {

        Teacher teacher = teacherRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), teacher.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtUtil.generateToken(teacher.getEmail(), teacher.getRole());
    }

    @Override
    public String studentLogin(String rollNo, String password) {

        Student student = studentRepository.findByRollNo(rollNo)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(password, student.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtUtil.generateToken(student.getRollNo(), "STUDENT");
    }

}
