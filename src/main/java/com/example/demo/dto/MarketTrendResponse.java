package com.example.demo.dto;

import java.util.List;

public class MarketTrendResponse {

    private String marketDemand;     // e.g. "CRITICAL DEMAND (94/100)"
    private String futureOutlook;    // 2-sentence market trajectory summary
    private List<String> hotRoles;   // Top 3 highest paying roles in this niche
    private List<String> highRoiSkills; // Skills to learn immediately
    private List<String> redOceanSkills; // Saturated skills to avoid

    public MarketTrendResponse() {}

    public MarketTrendResponse(String marketDemand, String futureOutlook, List<String> hotRoles, List<String> highRoiSkills, List<String> redOceanSkills) {
        this.marketDemand = marketDemand;
        this.futureOutlook = futureOutlook;
        this.hotRoles = hotRoles;
        this.highRoiSkills = highRoiSkills;
        this.redOceanSkills = redOceanSkills;
    }

    public String getMarketDemand() { return marketDemand; }
    public void setMarketDemand(String marketDemand) { this.marketDemand = marketDemand; }

    public String getFutureOutlook() { return futureOutlook; }
    public void setFutureOutlook(String futureOutlook) { this.futureOutlook = futureOutlook; }

    public List<String> getHotRoles() { return hotRoles; }
    public void setHotRoles(List<String> hotRoles) { this.hotRoles = hotRoles; }

    public List<String> getHighRoiSkills() { return highRoiSkills; }
    public void setHighRoiSkills(List<String> highRoiSkills) { this.highRoiSkills = highRoiSkills; }

    public List<String> getRedOceanSkills() { return redOceanSkills; }
    public void setRedOceanSkills(List<String> redOceanSkills) { this.redOceanSkills = redOceanSkills; }
}