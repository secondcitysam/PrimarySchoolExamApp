package com.school.examportal.dto;

import jakarta.validation.constraints.*;
import lombok.Getter; import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class QuestionCreateRequest {

    @NotBlank
    private String text;

    @NotNull
    private Integer marks;   // ✅ ADD THIS

    @Size(min = 4, max = 4)
    private List<String> options;

    @NotNull
    private Integer correctIndex;
}
