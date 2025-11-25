package com.skillbank.dto;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AdResponseDTO {
    private Long id;
    private String title;
    private String description;
    private String categoryName;
    private String authorName;
    private LocalDateTime createdAd;
}