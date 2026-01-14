package com.school.examportal.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "answer")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Answer {
//redis-cli ping
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Question question;

    @ManyToOne(optional = false)
    private Option selectedOption;

    @ManyToOne(optional = false)
    private TestAttempt attempt;
}
