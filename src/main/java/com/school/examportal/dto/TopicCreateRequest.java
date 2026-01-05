package com.school.examportal.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter; import lombok.Setter;

@Getter @Setter
public class TopicCreateRequest {
    @NotBlank
    private String name;
    private String description;
}
