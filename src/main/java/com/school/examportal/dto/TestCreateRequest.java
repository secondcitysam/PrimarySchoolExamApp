package com.school.examportal.dto;

import jakarta.validation.constraints.*;
import lombok.Getter; import lombok.Setter;

@Getter @Setter
public class TestCreateRequest {

    @NotBlank
    private String title;

    @Min(1) @Max(4)
    private int standard;

    @Min(1)
    private int durationMinutes;

    @Min(1)
    private int totalMarks;

    @NotNull
    private Long topicId;
}
