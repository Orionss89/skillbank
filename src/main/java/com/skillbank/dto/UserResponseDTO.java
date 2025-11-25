package com.skillbank.dto;
import lombok.Data;

@Data
public class UserResponseDTO {
    private Long id;
    private String username;
    private int balance;
}