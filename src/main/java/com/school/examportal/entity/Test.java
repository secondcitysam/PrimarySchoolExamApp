package com.school.examportal.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "test")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String title;

    @Column(nullable=false)
    private int standard; // 1–4

    @Column(nullable=false)
    private int durationMinutes;

    @Column(nullable=false)
    private int totalMarks;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private TestStatus status = TestStatus.DRAFT;

    @ManyToOne(optional=false)
    @JoinColumn(name = "topic_id")
    private Topic topic;

    private LocalDateTime createdAt = LocalDateTime.now();
}
