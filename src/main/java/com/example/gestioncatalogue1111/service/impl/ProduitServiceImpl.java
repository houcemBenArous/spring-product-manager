package com.example.gestioncatalogue1111.service.impl;

import com.example.gestioncatalogue1111.entities.Produit;
import com.example.gestioncatalogue1111.repository.ProduitRepository;
import com.example.gestioncatalogue1111.service.ProduitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProduitServiceImpl implements ProduitService {

    private final ProduitRepository produitRepository;

    @Override
    public void create(Produit produit) {
         produitRepository.save(produit);
    }

    @Override
    public Produit getById(Long id) {
        return produitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit not found with id " + id));
    }

    @Override
    public List<Produit> getAll() {
        return produitRepository.findAll();
    }

    @Override
    public void update(Produit produit) {
        Produit existing = produit;
        if (produit.getNom() != null) existing.setNom(produit.getNom());
        existing.setPrix(produit.getPrix());
        existing.setQuantite(produit.getQuantite());
        existing.setPhoto(produit.getPhoto());
        if (produit.getCategorie() != null) existing.setCategorie(produit.getCategorie());
        produitRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        if (!produitRepository.existsById(id)) {
            throw new RuntimeException("Produit not found with id " + id);
        }
        produitRepository.deleteById(id);
    }

    @Override
    public List<Produit> searchByNom(String keyword) {
        return produitRepository.findByNomContains(keyword);
    }

    @Override
    public List<Produit> getByCategorieId(String categorieId) {
        return produitRepository.findByCategorie(categorieId);
    }

    @Override
    public String uploadImage(MultipartFile file) throws IOException {
        String name = file.getOriginalFilename();
        String newname = System.currentTimeMillis() + "_" + name;
        Path p = Paths.get(System.getProperty("user.home")+"/imageGL");
        if (!Files.exists(p)) {
            Files.createDirectory(p);
        }
        Path pFile = p.resolve(newname);
        Files.write(pFile, file.getBytes());
        return newname;
    }


    @Override
    public byte[] getImage(Long id) throws IOException {
        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit not found with id " + id));
        
        String nomPhoto = produit.getPhoto();
        if (nomPhoto == null || nomPhoto.isEmpty()) {
            throw new RuntimeException("No image found for product with id " + id);
        }
        
        Path p = Paths.get(System.getProperty("user.home")+"/imageGL");
        Path imagePath = p.resolve(nomPhoto);
        
        if (!Files.exists(imagePath)) {
            throw new RuntimeException("Image file not found: " + nomPhoto);
        }
        
        return Files.readAllBytes(imagePath);
    }
}
