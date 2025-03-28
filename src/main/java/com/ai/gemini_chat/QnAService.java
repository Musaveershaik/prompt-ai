package com.ai.gemini_chat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class QnAService {
    // Access to APIKey and URL [Gemini]
    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    @Value("${GEMINI_API_KEY}")
    private String geminiApiKey;

    private final WebClient webClient;

    public QnAService(WebClient.Builder webClient) {
        this.webClient = webClient.build();
    }

    public String getAnswer(String question) {
        try {
            if (geminiApiKey == null || geminiApiKey.trim().isEmpty()) {
                return "Error: Gemini API key is not configured. Please set it in the Secrets tool.";
            }

            // Construct the request payload
            Map<String, Object> requestBody = Map.of(
                    "contents", new Object[] {
                            Map.of("parts", new Object[] {
                                    Map.of("text", question)
                            })
                    }
            );

            // Make API Call
            String response = webClient.post()
                    .uri(geminiApiUrl + geminiApiKey)
                    .header("Content-Type", "application/json")
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            return response != null ? response : "Error: No response from API";
        } catch (Exception e) {
            String errorMessage = e.getMessage();
            if (errorMessage.contains("403")) {
                return "Error: Invalid API key or unauthorized access. Please check your Gemini API key.";
            }
            return "Error: " + errorMessage;
        }
    }
}
