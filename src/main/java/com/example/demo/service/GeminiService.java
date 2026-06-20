package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class GeminiService {

	@Value("${openrouter.api.key}")
	private String apiKey;
    private final WebClient webClient;

    public GeminiService() {
        this.webClient = WebClient.builder().build();
    }

    public String askGemini(String prompt) {

        try {

        	String requestBody = """
        			{
        			  "model": "deepseek/deepseek-chat-v3-0324",
        			  "messages": [
        			    {
        			      "role": "user",
        			      "content": "%s"
        			    }
        			  ]
        			}
        			""".formatted(prompt);
        	System.out.println("AI METHOD CALLED");
        	System.out.println("API KEY: " + apiKey.substring(0, 10));

        	return webClient.post()
        	        .uri("https://openrouter.ai/api/v1/chat/completions")
        	        .header("Authorization", "Bearer " + apiKey)
        	        .header("Content-Type", "application/json")
        	        .header("HTTP-Referer", "http://localhost:8080")
        	        .header("X-Title", "Career Assistant")
        	        .bodyValue(requestBody)
        	        .retrieve()
        	        .bodyToMono(String.class)
        	        .block();

        } catch (Exception e) {

        	e.printStackTrace();
            return e.toString();
        }
    }
}