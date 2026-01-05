package com.school.examportal.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "option_item")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String text;

    @Column(nullable=false)
    private boolean correct;

    @Column(nullable=false)
    private int position; // 1–4

    @ManyToOne(optional=false)
    @JoinColumn(name = "question_id")
    private Question question;
}
