
package com.ai.gemini_chat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Map;

@Service
public class QnAService {
    private final String geminiApiUrl;
    private final String geminiApiKey;
    private final WebClient webClient;

    public QnAService(
            WebClient.Builder webClientBuilder,
            @Value("${gemini.api.url:}") String geminiApiUrl,
            @Value("${GEMINI_API_KEY:}") String geminiApiKey) {
        this.geminiApiUrl = geminiApiUrl;
        this.geminiApiKey = geminiApiKey;
        this.webClient = webClientBuilder.build();
    }

    public String getAnswer(String question) {
        try {
            if (geminiApiKey == null || geminiApiKey.trim().isEmpty()) {
                return "Error: Gemini API key is not configured. Please set it in the environment variables.";
            }

            Map<String, Object> requestBody = Map.of(
                    "contents", new Object[] {
                            Map.of("parts", new Object[] {
                                    Map.of("text", question)
                            })
                    }
            );

            String response = webClient.post()
                    .uri(geminiApiUrl + geminiApiKey)
                    .header("Content-Type", "application/json")
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            return response != null ? response : "No response from API";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
