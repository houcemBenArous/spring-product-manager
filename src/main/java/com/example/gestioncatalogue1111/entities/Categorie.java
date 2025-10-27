package com.example.gestioncatalogue1111.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Categorie {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String nom;

    @JsonIgnore
    @OneToMany(mappedBy = "categorie" ,cascade = CascadeType.ALL)
    List<Produit> produits;
}