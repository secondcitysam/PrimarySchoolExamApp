package com.school.examportal.dto;

import jakarta.validation.constraints.*;
import lombok.Getter; import lombok.Setter;

import java.util.List;

@Getter @Setter
public class QuestionCreateRequest {

    @NotBlank
    private String text;

    @Min(1)
    private int marks;

    @NotNull
    private Long testId;

    @Size(min = 4, max = 4)
    private List<OptionRequest> options;
}
