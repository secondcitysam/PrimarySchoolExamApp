package com.school.examportal.service.impl;

import com.school.examportal.dto.TopicCreateRequest;
import com.school.examportal.entity.Topic;
import com.school.examportal.repository.TopicRepository;
import com.school.examportal.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {

    private final TopicRepository repo;

    @Override
    public void create(TopicCreateRequest req) {
        if (repo.existsByName(req.getName())) {
            throw new RuntimeException("Topic already exists");
        }
        Topic t = new Topic();
        t.setName(req.getName());
        t.setDescription(req.getDescription());
        repo.save(t);
    }

    @Override
    public List<Topic> findAll() {
        return repo.findAll();
    }
}
