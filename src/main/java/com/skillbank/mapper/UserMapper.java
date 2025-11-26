package com.skillbank.mapper;

import com.skillbank.dto.UserResponseDTO;
import com.skillbank.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDTO toDto(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        if (user.getWallet() != null) {
            dto.setBalance(user.getWallet().getBalance());
        }
        return dto;
    }
}