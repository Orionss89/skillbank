package com.skillbank.mapper;

import com.skillbank.dto.UserResponseDTO;
import com.skillbank.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDTO toDto(User user) {
        if (user == null) return null;

        return UserResponseDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .balance(user.getWallet() != null ? user.getWallet().getBalance() : 0)
                .build();
    }
}