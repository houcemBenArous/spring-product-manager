package com.example.gestioncatalogue1111.repository;

import com.example.gestioncatalogue1111.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, String> {
     Categorie findByNom(String nom);
}
