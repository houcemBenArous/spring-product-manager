package com.example.gestioncatalogue1111.service;

import com.example.gestioncatalogue1111.entities.Produit;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProduitService {
    void create(Produit produit);
    Produit getById(Long id);
    List<Produit> getAll();
    void update(Produit produit);
    void delete(Long id);

    List<Produit> searchByNom(String keyword);
    List<Produit> getByCategorieId(String categorieId);

    String uploadImage(MultipartFile file) throws IOException;
    byte[] getImage(Long id) throws IOException;
}
