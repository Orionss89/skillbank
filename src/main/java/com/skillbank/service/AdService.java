package com.skillbank.service;

import com.skillbank.dto.AdDTO;
import com.skillbank.dto.AdResponseDTO;
import com.skillbank.exception.ResourceNotFoundException;
import com.skillbank.mapper.AdMapper;
import com.skillbank.model.*;
import com.skillbank.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdService {

    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final AdMapper adMapper;

    @Transactional
    public void addAd(AdDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Użytkownik nie istnieje"));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Kategoria nie istnieje"));

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
                .map(adMapper::toDto)
                .collect(Collectors.toList());
    }
}