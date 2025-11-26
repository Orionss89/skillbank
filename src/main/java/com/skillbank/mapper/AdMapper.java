package com.skillbank.mapper;

import com.skillbank.dto.AdResponseDTO;
import com.skillbank.model.Ad;
import org.springframework.stereotype.Component;

@Component
public class AdMapper {
    public AdResponseDTO toDto(Ad ad) {
        AdResponseDTO dto = new AdResponseDTO();
        dto.setId(ad.getId());
        dto.setTitle(ad.getTitle());
        dto.setDescription(ad.getDescription());
        dto.setCreatedAd(ad.getCreatedAd());
        dto.setCategoryName(ad.getCategory().getName());
        dto.setAuthorName(ad.getUser().getUsername());
        return dto;
    }
}