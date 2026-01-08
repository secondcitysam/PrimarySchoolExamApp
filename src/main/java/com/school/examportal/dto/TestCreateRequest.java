package com.school.examportal.dto;

import jakarta.validation.constraints.*;
import lombok.Getter; import lombok.Setter;
@Getter
@Setter
public class TestCreateRequest {

    @NotBlank
    private String title;

    @NotNull
    private Integer durationMinutes;

    @NotNull
    private Integer totalMarks;

    @NotNull
    private Integer standard;

    @NotNull
    private Long topicId;
}
