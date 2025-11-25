package com.skillbank.controller;

import com.skillbank.dto.AdDTO;
import com.skillbank.dto.AdResponseDTO; // <--- WAŻNY IMPORT (Nowy typ)
import com.skillbank.service.AdService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ads")
public class AdController {

    private final AdService adService;

    public AdController(AdService adService) {
        this.adService = adService;
    }

    @PostMapping
    public ResponseEntity<String> addAd(@RequestBody AdDTO dto) {
        adService.addAd(dto);
        return ResponseEntity.ok("Ogłoszenie dodane!");
    }

    @GetMapping
    public ResponseEntity<List<AdResponseDTO>> getAllAds() {
        return ResponseEntity.ok(adService.getAllAds());
    }
}