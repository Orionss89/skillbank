package com.skillbank.service;

import com.skillbank.dto.RegisterDTO;
import com.skillbank.model.Role;
import com.skillbank.model.User;
import com.skillbank.repository.RoleRepository;
import com.skillbank.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public void registerUSer(RegisterDTO dto) {
        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new RuntimeException("Użytkownik o takim loginie już istnieje!");
        }
        User newUser = new User();
        newUser.setUsername(dto.getUsername());
        newUser.setPassword(dto.getPassword());
        newUser.setBalance(5);

        Role userRole = roleRepository.findByName("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        newUser.setRoles(roles);
        userRepository.save(newUser);
    }
}
