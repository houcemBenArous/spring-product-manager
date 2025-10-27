package com.example.gestioncatalogue1111.service.impl;

import com.example.gestioncatalogue1111.entities.Categorie;
import com.example.gestioncatalogue1111.repository.CategorieRepository;
import com.example.gestioncatalogue1111.service.CategorieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategorieServiceImpl implements CategorieService {
    private final CategorieRepository categorieRepository;

    @Override
    public void create(Categorie categorie) {
        categorieRepository.save(categorie);
    }

    @Override
    public List<Categorie> getAll() {
        return categorieRepository.findAll();
    }

    @Override
    public Categorie getById(String id) {
        return categorieRepository.findById(id).orElse(null);
    }

    @Override
    public void update(Categorie categorie) {
        categorieRepository.save(categorie);
    }

    @Override
    public void delete(Categorie categorie) {
        categorieRepository.delete(categorie);
    }
}