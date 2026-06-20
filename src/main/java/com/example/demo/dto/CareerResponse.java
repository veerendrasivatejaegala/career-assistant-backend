package com.example.demo.dto;

import java.util.List;

public class CareerResponse {

    private String career;
    private String matchScore;
    private List<String> missingSkills;
    private List<String> roadmap;

    public CareerResponse() {
    }

    public CareerResponse(
            String career,
            String matchScore,
            List<String> missingSkills,
            List<String> roadmap) {

        this.career = career;
        this.matchScore = matchScore;
        this.missingSkills = missingSkills;
        this.roadmap = roadmap;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getMatchScore() {
        return matchScore;
    }

    public void setMatchScore(String matchScore) {
        this.matchScore = matchScore;
    }

    public List<String> getMissingSkills() {
        return missingSkills;
    }

    public void setMissingSkills(List<String> missingSkills) {
        this.missingSkills = missingSkills;
    }

    public List<String> getRoadmap() {
        return roadmap;
    }

    public void setRoadmap(List<String> roadmap) {
        this.roadmap = roadmap;
    }
}