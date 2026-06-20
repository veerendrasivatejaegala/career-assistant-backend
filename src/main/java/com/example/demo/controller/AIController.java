package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import com.example.demo.dto.CareerResponse;
import com.example.demo.service.CareerService;

@RestController
@RequestMapping("/ai")
public class AIController {

    private final CareerService careerService;

    public AIController(CareerService careerService) {
        this.careerService = careerService;
    }

    @GetMapping("/career")
    public CareerResponse careerAdvice(
            @RequestParam String skills, // Note: You might need to split this in frontend or adjust the service method
            @RequestParam Double cgpa) {
        
        // Passing generic 'skills' to the DSA/Java fields temporarily 
        // to utilize our newly structured AI service
        return careerService.recommendCareer(skills, skills, cgpa);
    }
}