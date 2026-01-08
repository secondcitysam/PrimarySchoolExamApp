package com.school.examportal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestionUpsertRequest {

    private Long questionId; // null = new

    @NotBlank
    private String text;

    @NotNull
    private Integer marks;

    @Size(min = 4, max = 4)
    private List<String> options;

    @NotNull
    private Integer correctIndex;
}
