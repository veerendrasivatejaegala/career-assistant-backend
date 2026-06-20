package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.SkillGapResponse;

@Service
public class SkillGapService {

    public SkillGapResponse analyzeSkills(
            String currentSkills,
            String targetRole) {

        List<String> missingSkills = new ArrayList<>();

        if(targetRole.equalsIgnoreCase(
                "Java Backend Developer")) {

            if(!currentSkills.contains("Spring Boot"))
                missingSkills.add("Spring Boot");

            if(!currentSkills.contains("REST APIs"))
                missingSkills.add("REST APIs");

            if(!currentSkills.contains("Docker"))
                missingSkills.add("Docker");

            if(!currentSkills.contains("AWS"))
                missingSkills.add("AWS");

            if(!currentSkills.contains("Microservices"))
                missingSkills.add("Microservices");
        }

        return new SkillGapResponse(
                targetRole,
                missingSkills);
    }
}