package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dto.RoadmapResponse;

@Service
public class RoadmapService {

    public RoadmapResponse generateRoadmap(String career) {

        if(career.equalsIgnoreCase(
                "Java Backend Developer")) {

            return new RoadmapResponse(
                    "Core Java + OOP",
                    "Collections + JDBC",
                    "Spring Boot",
                    "REST APIs + JWT",
                    "Microservices",
                    "AWS + Projects");
        }

        return new RoadmapResponse(
                "Programming Basics",
                "DSA",
                "Web Development",
                "Projects",
                "Cloud Basics",
                "Interview Prep");
    }
}