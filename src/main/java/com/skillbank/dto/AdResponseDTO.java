package com.skillbank.dto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class AdResponseDTO {
    private Long id;
    private String title;
    private String description;
    private String categoryName;
    private String authorName;
    private LocalDateTime createdAd;
}