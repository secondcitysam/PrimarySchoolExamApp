package com.school.examportal.service;

import com.school.examportal.dto.TopicCreateRequest;
import com.school.examportal.entity.Topic;

import java.util.List;

public interface TopicService {
    void create(TopicCreateRequest req);
    List<Topic> findAll();
}
