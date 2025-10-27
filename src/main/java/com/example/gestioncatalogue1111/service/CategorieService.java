package com.example.gestioncatalogue1111.service;

import com.example.gestioncatalogue1111.entities.Categorie;

import java.util.List;

public interface CategorieService {
    void create(Categorie categorie);
    public List<Categorie> getAll();
    public Categorie getById(String id);
    public void update(Categorie categorie);
    public void delete(Categorie categorie);


}
