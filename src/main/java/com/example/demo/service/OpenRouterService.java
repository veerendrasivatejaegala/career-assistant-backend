package com.example.demo.service;

import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OpenRouterService {

    @Value("${openrouter.api.key}")
    private String apiKey;

    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    public OpenRouterService(ObjectMapper objectMapper) {
        // THE FIX: Force Netty to use the standard JVM / OS built-in DNS resolver
        // This prevents the 5000ms UDP timeout when using mobile hotspots
        HttpClient httpClient = HttpClient.create()
                .resolver(DefaultAddressResolverGroup.INSTANCE);

        this.webClient = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
                
        this.objectMapper = objectMapper;
    }

    public String askAI(String prompt) {
        System.out.println("OPENROUTER SERVICE HIT (LIVE ONLINE ATTEMPT)");

        // Robust escaping to ensure the JSON payload never breaks
        String safePrompt = prompt.replace("\\", "\\\\")
                                  .replace("\"", "\\\"")
                                  .replace("\n", "\\n")
                                  .replace("\r", "");

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
        """.formatted(safePrompt);

        try {
            String rawResponse = webClient.post()
                    .uri("https://openrouter.ai/api/v1/chat/completions")
                    .header("Authorization", "Bearer " + apiKey)
                    .header("Content-Type", "application/json")
                    .header("HTTP-Referer", "http://localhost:8080")
                    .header("X-Title", "Career Assistant")
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            // Extract the actual AI message from the OpenRouter payload
            JsonNode rootNode = objectMapper.readTree(rawResponse);
            String aiContent = rootNode.path("choices").get(0).path("message").path("content").asText();

            // Clean up Markdown backticks if the AI wraps the JSON (e.g., ```json ... ```)
            if (aiContent.startsWith("```json")) {
                aiContent = aiContent.substring(7, aiContent.length() - 3).trim();
            } else if (aiContent.startsWith("```")) {
                aiContent = aiContent.substring(3, aiContent.length() - 3).trim();
            }

            return aiContent;

        } catch (Exception e) {
        	
            e.printStackTrace();
            throw new RuntimeException("AI request failed: " + e.getMessage());
        }
    }
}