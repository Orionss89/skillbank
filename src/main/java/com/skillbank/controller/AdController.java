package com.skillbank.controller;

import com.skillbank.dto.AdDTO;
import com.skillbank.dto.AdResponseDTO;
import com.skillbank.service.AdService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ads")
@RequiredArgsConstructor
public class AdController {

    private final AdService adService;

    @PostMapping
    public ResponseEntity<String> addAd(@Valid @RequestBody AdDTO dto) {
        adService.addAd(dto);
        return ResponseEntity.ok("Ogłoszenie dodane!");
    }

    @GetMapping
    public ResponseEntity<List<AdResponseDTO>> getAllAds() {
        return ResponseEntity.ok(adService.getAllAds());
    }
}