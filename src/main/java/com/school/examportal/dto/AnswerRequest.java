package com.school.examportal.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerRequest {

    private Long attemptId;
    private Long questionId;
    private Long optionId;
}
