package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Assessment;
import com.example.demo.service.AssessmentService;

@RestController
@RequestMapping("/assessment")
public class AssessmentController {

    private final AssessmentService service;

    public AssessmentController(
            AssessmentService service) {

        this.service = service;
    }

    @PostMapping
    public Assessment saveAssessment(
            @RequestBody Assessment assessment) {

        return service.save(assessment);
    }
}