package com.example.gestioncatalogue1111;

import com.example.gestioncatalogue1111.entities.Categorie;
import com.example.gestioncatalogue1111.entities.Produit;
import com.example.gestioncatalogue1111.repository.CategorieRepository;
import com.example.gestioncatalogue1111.repository.ProduitRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class Gestioncatalogue1111Application {


    CommandLineRunner runner(ProduitRepository produitRepository, CategorieRepository categorieRepository){
        return args -> {
            /*categorieRepository.save(new Categorie(UUID.randomUUID().toString(),nom: "information", produits: null));*/
            categorieRepository.save(
                    Categorie.builder()
                            .nom("informatique")
                            .id(UUID.randomUUID().toString())
                            .build()

            );

            produitRepository.save(
                    Produit.builder()
                            .nom("pc portable")
                            .prix(3000)
                            .quantite(5)
                            .categorie(categorieRepository.findByNom("informatique"))
                            .build()
            );

            produitRepository.save(
                    Produit.builder()
                            .nom("sourie")
                            .prix(300)
                            .quantite(7)
                            .categorie(categorieRepository.findByNom("informatique"))
                            .build()
            );
        };
    }
    public static void main(String[] args) {
        SpringApplication.run(Gestioncatalogue1111Application.class, args);
    }

}
