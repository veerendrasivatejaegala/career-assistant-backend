package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.SkillGapResponse;
import com.example.demo.service.SkillGapService;

@RestController
@RequestMapping("/skill-gap")
public class SkillGapController {

    private final SkillGapService service;

    public SkillGapController(
            SkillGapService service) {

        this.service = service;
    }

    @GetMapping
    public SkillGapResponse getSkillGap(

            @RequestParam String currentSkills,
            @RequestParam String targetRole) {

        return service.analyzeSkills(
                currentSkills,
                targetRole);
    }
}