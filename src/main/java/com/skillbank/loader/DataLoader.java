package com.skillbank.loader;

import com.skillbank.model.Category;
import com.skillbank.model.Role;
import com.skillbank.repository.CategoryRepository;
import com.skillbank.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final CategoryRepository categoryRepository;

    public DataLoader(RoleRepository roleRepository, CategoryRepository categoryRepository) {
        this.roleRepository = roleRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.count() == 0) {
            Role userRole = new Role();
            userRole.setName("ROLE_USER");
            roleRepository.save(userRole);

            Role adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            roleRepository.save(adminRole);

            System.out.println("--- ADDED STARTING ROLES ---");
        }
        if (categoryRepository.count() == 0) {
            createCategory("Edukacja");
            createCategory("Naprawy Domowe");
            createCategory("Ogród");
            createCategory("Opieka nad zwierzętami");
            createCategory("Transport");

            System.out.println("--- ADDED STARTING ROLES ---");
        }
    }

    private void createCategory(String name) {
        Category category = new Category();
        category.setName(name);
        categoryRepository.save(category);
    }


}
