package com.skillbank.controller;

import com.skillbank.service.MotivationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/motivation")
public class MotivationController {

    private final MotivationService motivationService;

    public MotivationController(MotivationService motivationService) {
        this.motivationService = motivationService;
    }

    @GetMapping
    public ResponseEntity<String> getQuote() {
        String quote = motivationService.getMotivation();
        return ResponseEntity.ok(quote);
    }
}