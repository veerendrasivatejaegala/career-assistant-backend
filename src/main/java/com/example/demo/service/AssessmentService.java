package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.Assessment;
import com.example.demo.repository.AssessmentRepository;

@Service
public class AssessmentService {

    private final AssessmentRepository repository;

    public AssessmentService(
            AssessmentRepository repository) {

        this.repository = repository;
    }

    public Assessment save(Assessment assessment) {
        return repository.save(assessment);
    }
}