package com.school.examportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.school.examportal")
public class ExamportalApplication {
	public static void main(String[] args) {
		SpringApplication.run(ExamportalApplication.class, args);
	}
}

