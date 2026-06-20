package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.CareerResponse;
import com.example.demo.service.CareerService;

@RestController
@RequestMapping("/career")
public class CareerController {

    private final CareerService service;

    public CareerController(CareerService service) {
        this.service = service;
    }

    @GetMapping("/recommend")
    public CareerResponse recommendCareer(

            @RequestParam String javaSkill,
            @RequestParam String dsaSkill,
            @RequestParam Double cgpa) {

        return service.recommendCareer(
                javaSkill,
                dsaSkill,
                cgpa);
    }
}