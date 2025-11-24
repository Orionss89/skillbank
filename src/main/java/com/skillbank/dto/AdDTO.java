package com.skillbank.dto;

import lombok.Data;

@Data
public class AdDTO {
    private String title;
    private String description;
    private Long categoryId;
    private Long userId;
}
