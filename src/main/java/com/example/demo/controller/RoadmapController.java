package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.RoadmapResponse;
import com.example.demo.service.RoadmapService;

@RestController
@RequestMapping("/roadmap")
public class RoadmapController {

    private final RoadmapService service;

    public RoadmapController(
            RoadmapService service) {

        this.service = service;
    }

    @GetMapping
    public RoadmapResponse getRoadmap(
            @RequestParam String career) {

        return service.generateRoadmap(career);
    }
}