package com.skillbank.loader;

import com.skillbank.model.Category;
import com.skillbank.model.Role;
import com.skillbank.repository.CategoryRepository;
import com.skillbank.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        if (0 == roleRepository.count()) {
            saveRole("ROLE_USER");
            saveRole("ROLE_ADMIN");
            log.info("--- Roles initialized ---");
        }

        if (0 == categoryRepository.count()) {
            saveCategory("Education");
            saveCategory("Repairs");
            log.info("--- Categories initialized ---");
        }
    }

    private void saveRole(String name) {
        Role role = new Role();
        role.setName(name);
        roleRepository.save(role);
    }

    private void saveCategory(String name) {
        Category category = new Category();
        category.setName(name);
        categoryRepository.save(category);
    }
}