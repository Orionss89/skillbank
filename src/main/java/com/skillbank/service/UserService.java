package com.skillbank.service;

import com.skillbank.dto.RegisterDTO;
import com.skillbank.dto.UserResponseDTO;
import com.skillbank.mapper.UserMapper;
import com.skillbank.model.*;
import com.skillbank.repository.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper; // Nowość

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Transactional
    public UserResponseDTO registerUser(RegisterDTO dto) {
        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new RuntimeException("Użytkownik już istnieje!");
        }

        User newUser = new User();
        newUser.setUsername(dto.getUsername());
        newUser.setPassword(passwordEncoder.encode(dto.getPassword()));

        Wallet wallet = new Wallet();
        wallet.setBalance(5);
        wallet.setUser(newUser);
        newUser.setWallet(wallet);

        Role userRole = roleRepository.findByName("ROLE_USER");
        if(userRole == null) throw new RuntimeException("Błąd systemu: Brak roli USER");

        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        newUser.setRoles(roles);

        User savedUser = userRepository.save(newUser);
        return userMapper.toDto(savedUser);
    }
}