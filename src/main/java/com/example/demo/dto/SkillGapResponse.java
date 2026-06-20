package com.example.demo.dto;

import java.util.List;

public class SkillGapResponse {

    private String targetRole;
    private List<String> missingSkills;

    public SkillGapResponse() {
    }

    public SkillGapResponse(
            String targetRole,
            List<String> missingSkills) {

        this.targetRole = targetRole;
        this.missingSkills = missingSkills;
    }

    public String getTargetRole() {
        return targetRole;
    }

    public void setTargetRole(String targetRole) {
        this.targetRole = targetRole;
    }

    public List<String> getMissingSkills() {
        return missingSkills;
    }

    public void setMissingSkills(List<String> missingSkills) {
        this.missingSkills = missingSkills;
    }
}