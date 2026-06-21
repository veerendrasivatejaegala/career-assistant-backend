package com.example.demo.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.example.demo.dto.CareerAnalysisResponse;
import com.example.demo.dto.CareerResponse;
import com.example.demo.dto.RoadmapResponse;
import com.example.demo.service.CareerService;

@RestController
@RequestMapping("/career-analysis")
public class CareerAnalysisController {

    private final CareerService careerService;

    public CareerAnalysisController(CareerService careerService) {
        this.careerService = careerService;
    }

    @GetMapping
    public CareerAnalysisResponse analyzeCareer(
            @RequestParam String javaSkill,
            @RequestParam String dsaSkill,
            @RequestParam Double cgpa) {

        // 1. Fetch the dynamic AI response
        CareerResponse aiCareer = careerService.recommendCareer(javaSkill, dsaSkill, cgpa);

        // 2. Map the dynamic list to your fixed Month1-Month6 Roadmap DTO
        RoadmapResponse roadmap = new RoadmapResponse();
        List<String> dynamicRoadmap = aiCareer.getRoadmap();
        
        if (dynamicRoadmap != null) {
            roadmap.setMonth1(dynamicRoadmap.size() > 0 ? dynamicRoadmap.get(0) : "Days 1-10: Review Basics");
            roadmap.setMonth2(dynamicRoadmap.size() > 1 ? dynamicRoadmap.get(1) : "Days 11-20: Intermediate Concepts");
            roadmap.setMonth3(dynamicRoadmap.size() > 2 ? dynamicRoadmap.get(2) : "Days 21-30: Advanced Implementation");
            roadmap.setMonth4(dynamicRoadmap.size() > 3 ? dynamicRoadmap.get(3) : "Days 31-40: Build Projects");
            roadmap.setMonth5(dynamicRoadmap.size() > 4 ? dynamicRoadmap.get(4) : "Days 41-50: Cloud & Deployment");
            roadmap.setMonth6(dynamicRoadmap.size() > 5 ? dynamicRoadmap.get(5) : "Days 51-60: Interview Preparation");
        }

        // 3. Return the fully structured JSON to the frontend
        return new CareerAnalysisResponse(
                aiCareer.getCareer(),
                aiCareer.getMatchScore(),
                aiCareer.getMissingSkills(), // This now replaces SkillGapService
                roadmap                      // This now replaces RoadmapService
        );
    }
}