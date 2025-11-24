package com.skillbank.service;

import com.skillbank.dto.AdDTO;
import com.skillbank.model.Ad;
import com.skillbank.model.Category;
import com.skillbank.model.User;
import com.skillbank.repository.AdRepository;
import com.skillbank.repository.CategoryRepository;
import com.skillbank.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdService {

    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public AdService(AdRepository adRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.adRepository = adRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    public void addAd(AdDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Nie znaleziono kategorii o ID: " + dto.getCategoryId()));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Nie znaleziono kategorii o ID: " + dto.getCategoryId()));

        Ad ad = new Ad();
        ad.setTitle(dto.getTitle());
        ad.setDescription(dto.getDescription());
        ad.setUser(user);
        ad.setCategory(category);
        adRepository.save(ad);
    }
   public List<Ad> getAllAds(){
       return adRepository.findAll();
   }
}
