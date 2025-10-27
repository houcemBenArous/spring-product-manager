package com.example.gestioncatalogue1111.repository;

import com.example.gestioncatalogue1111.entities.Categorie;
import com.example.gestioncatalogue1111.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
    List<Produit> findByNomContains(String nom);

    @Query("select p from Produit p where p.categorie.id=:x")
    List<Produit> findByCategorie(@Param("x") String idCategorie);

}
