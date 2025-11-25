package com.skillbank.service;

import com.skillbank.dto.AdDTO;
import com.skillbank.dto.AdResponseDTO;
import com.skillbank.mapper.AdMapper;
import com.skillbank.model.*;
import com.skillbank.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdService {
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final AdMapper adMapper;

    public AdService(AdRepository adRepository, UserRepository userRepository, CategoryRepository categoryRepository, AdMapper adMapper) {
        this.adRepository = adRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.adMapper = adMapper;
    }

    @Transactional
    public void addAd(AdDTO dto) {
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Kategoria o podanym ID nie istnieje!"));

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Użytkownik nie istnieje!"));

        Ad ad = new Ad();
        ad.setTitle(dto.getTitle());
        ad.setDescription(dto.getDescription());
        ad.setCreatedAd(LocalDateTime.now());
        ad.setUser(user);
        ad.setCategory(category);

        adRepository.save(ad);
    }

    public List<AdResponseDTO> getAllAds() {
        return adRepository.findAll().stream()
                .map(adMapper::toDto) // Konwersja Encja -> DTO
                .collect(Collectors.toList());
    }
}