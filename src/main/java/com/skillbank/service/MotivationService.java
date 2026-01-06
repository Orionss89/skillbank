package com.skillbank.service;

import com.skillbank.dto.QuoteDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MotivationService {

    private final RestTemplate restTemplate;

    public MotivationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getMotivation() {
        String url = "https://dummyjson.com/quotes/random";

        try {
            QuoteDTO response = restTemplate.getForObject(url, QuoteDTO.class);
            if (response != null && response.getQuote() != null) {
                return "\"" + response.getQuote() + "\" - " + response.getAuthor();
            }
        } catch (Exception e) {
            System.err.println("Failed to load quote: " + e.getMessage());
        }
        return "Remember: Even the longest journey begins with a single step. (Offline Mode)";
    }
}