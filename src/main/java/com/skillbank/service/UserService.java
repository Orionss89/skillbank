package com.skillbank.service;

import com.skillbank.dto.RegisterDTO;
import com.skillbank.dto.UserResponseDTO;
import com.skillbank.exception.BusinessException;
import com.skillbank.exception.ResourceNotFoundException;
import com.skillbank.mapper.UserMapper;
import com.skillbank.model.*;
import com.skillbank.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Transactional
    public UserResponseDTO registerUser(RegisterDTO dto) {
        log.info("Rejestracja nowego użytkownika: {}", dto.getUsername());

        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new BusinessException("Użytkownik o takim loginie już istnieje");
        }

        User newUser = new User();
        newUser.setUsername(dto.getUsername());
        newUser.setPassword(passwordEncoder.encode(dto.getPassword()));

        Wallet wallet = new Wallet();
        wallet.setBalance(5);
        wallet.setUser(newUser);
        newUser.setWallet(wallet);

        Role userRole = roleRepository.findByName("ROLE_USER");
        if (userRole == null) {
            log.error("Błąd krytyczny: Brak roli ROLE_USER w bazie danych!");
            throw new ResourceNotFoundException("Błąd systemu: Rola domyślna nie istnieje");
        }

        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        newUser.setRoles(roles);

        User savedUser = userRepository.save(newUser);
        log.info("Użytkownik {} zarejestrowany z ID {}", savedUser.getUsername(), savedUser.getId());

        return userMapper.toDto(savedUser);
    }
}