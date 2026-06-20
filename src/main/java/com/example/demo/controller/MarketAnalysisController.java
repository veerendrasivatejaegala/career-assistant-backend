package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import com.example.demo.dto.MarketTrendResponse;
import com.example.demo.service.CareerService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/market-trends")
public class MarketAnalysisController {

    private final CareerService careerService;

    public MarketAnalysisController(CareerService careerService) {
        this.careerService = careerService;
    }

    @GetMapping
    public MarketTrendResponse getMarketInsights(
            @RequestParam String interests,
            @RequestParam Double cgpa) {
        
        return careerService.analyzeMarketTrend(interests, cgpa);
    }
}