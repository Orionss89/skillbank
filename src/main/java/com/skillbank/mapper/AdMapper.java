package com.skillbank.mapper;

import com.skillbank.dto.AdResponseDTO;
import com.skillbank.model.Ad;
import org.springframework.stereotype.Component;

@Component
public class AdMapper {
    public AdResponseDTO toDto(Ad ad) {

        if (ad == null) return null;

        return AdResponseDTO.builder()
                .id(ad.getId())
                .title(ad.getTitle())
                .description(ad.getDescription())
                .createdAd(ad.getCreatedAd())
                .categoryName(ad.getCategory() != null ? ad.getCategory().getName() : "No category")
                .authorName(ad.getUser() != null ? ad.getUser().getUsername() : "Unknown")
                .build();
    }
}