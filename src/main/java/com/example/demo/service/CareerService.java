package com.example.demo.service;

import java.util.Arrays;
import org.springframework.stereotype.Service;
import com.example.demo.dto.CareerResponse;
import com.example.demo.dto.MarketTrendResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CareerService {

    private final OpenRouterService aiService;
    private final ObjectMapper objectMapper;

    public CareerService(OpenRouterService aiService, ObjectMapper objectMapper) {
        this.aiService = aiService;
        this.objectMapper = objectMapper;
    }
    
public MarketTrendResponse analyzeMarketTrend(String interests, Double cgpa) {
        
        // Strict prompt forcing DeepSeek into a Tech Labor Economist persona
        String prompt = """
                Act as an elite Tech Labor Market Analyst in 2026. 
                A B.Tech student with a CGPA of %s expresses strong career interest in: "%s".
                
                Analyze the global job market trends for these specific interests.
                Return strictly a JSON object with no markdown and no extra prose, using exactly these keys:
                - "marketDemand": string (e.g. 'EXPLOSIVE GROWTH - 94/100')
                - "futureOutlook": string (2 concise sentences on the 3-year trajectory of this niche)
                - "hotRoles": array of 3 strings (top emerging job titles)
                - "highRoiSkills": array of 4 strings (high-leverage skills they should learn immediately)
                - "redOceanSkills": array of 3 strings (outdated or overly saturated skills in this niche to avoid)
                """.formatted(cgpa, interests);

        try {
            String jsonResponse = aiService.askAI(prompt);
            return objectMapper.readValue(jsonResponse, MarketTrendResponse.class);
        } catch (Exception e) {
            System.err.println("MARKET AI ERROR: " + e.getMessage());
            
            // Bulletproof fallback object if your Hotspot UDP times out again
            return new MarketTrendResponse(
                "HYPER-GROWTH SECTOR (92/100)",
                "The convergence of automated cloud deployments and AI threat vectors has turned this specific niche into a massive deficit sector. Companies are paying aggressive premiums for zero-trust specialists.",
                java.util.Arrays.asList("Cloud DevSecOps Lead", "AI Security Architect", "Zero-Trust Engineer"),
                java.util.Arrays.asList("Kubernetes Security", "eBPF", "OAuth2/OIDC", "Go Lang"),
                java.util.Arrays.asList("Manual QA Testing", "Basic Vanilla PHP", "Standard HTML/CSS")
            );
        }
    }

    public CareerResponse recommendCareer(String javaSkill, String dsaSkill, Double cgpa) {
        
        String prompt = """
                Analyze the following student profile and recommend a career path.
                Skills: Java - %s, DSA - %s
                CGPA: %s

                You MUST return ONLY a valid JSON object. Do not include any other text. 
                Use exactly these keys:
                - "career" (string: recommended job title)
                - "matchScore" (string: percentage)
                - "missingSkills" (array of strings)
                - "roadmap" (array of 6 strings, representing a 60-day plan divided into 6 stages of 10 days each: Days 1-10, Days 11-20, Days 21-30, Days 31-40, Days 41-50, and Days 51-60 respectively)
                """.formatted(javaSkill, dsaSkill, cgpa);

        try {
            // Attempt to fetch from AI
            String jsonResponse = aiService.askAI(prompt);
            return objectMapper.readValue(jsonResponse, CareerResponse.class);
            
        } catch (Exception e) {
            System.err.println("NETWORK/AI ERROR DETECTED: Falling back to local engine.");
            e.printStackTrace();
            
            // Safe hardcoded response fallback if internet/DNS fails
            if (cgpa >= 8.0 && javaSkill.toLowerCase().contains("intermediate")) {
                return new CareerResponse(
                    "Java Backend Developer (Offline Mode)", 
                    "85%", 
                    Arrays.asList("Spring Boot", "Docker", "AWS", "Microservices"), 
                    Arrays.asList(
                        "Days 1-10: Learn Spring Boot & Spring Data JPA fundamentals.", 
                        "Days 11-20: Build REST APIs & perform Database Integration.", 
                        "Days 21-30: Study microservices architecture patterns.",
                        "Days 31-40: Implement Spring Cloud Config and Service Discovery (Eureka).",
                        "Days 41-50: Study containerization with Docker and deployment via AWS ECS.",
                        "Days 51-60: Focus on System Design concepts and mock interviews."
                    )
                );
            } else {
                return new CareerResponse(
                    "Software Engineer (Offline Mode)", 
                    "75%", 
                    Arrays.asList("Data Structures", "System Design", "SQL Fundamentals"), 
                    Arrays.asList(
                        "Days 1-10: Learn basic Data Structures including Arrays, Linked Lists, and Hash Maps.", 
                        "Days 11-20: Understand sorting/searching algorithms and recursion basics.", 
                        "Days 21-30: Practice SQL queries, schema design, and normalization.",
                        "Days 31-40: Learn Object-Oriented Design principles and design patterns.",
                        "Days 41-50: Study scalability basics, horizontal/vertical scaling, and load balancing.",
                        "Days 51-60: Build a robust project, prepare your resume, and conduct mock interviews."
                    )
                );
            }
        }
    }
}

