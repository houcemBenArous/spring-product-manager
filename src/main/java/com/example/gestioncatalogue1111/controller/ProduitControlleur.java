package com.example.gestioncatalogue1111.controller;


import com.example.gestioncatalogue1111.entities.Produit;
import com.example.gestioncatalogue1111.service.impl.ProduitServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.hibernate.service.spi.InjectService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
@CrossOrigin("*")
public class ProduitControlleur {

    private ProduitServiceImpl serviceProduit;

    @GetMapping("/all")
    public List<Produit> findAllProducts(){
        return serviceProduit.getAll();
    }

    @GetMapping("/cat/{id}")
    public List<Produit> findProductByCategory(@PathVariable String id){
        return serviceProduit.getByCategorieId(id);
    }

    @GetMapping("/search")
    public List<Produit> findProductBySearch(@RequestParam String search){
        return serviceProduit.searchByNom(search);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id){
        serviceProduit.delete(id);
    }

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void addProduct(@RequestParam String produit, @RequestParam(required = false) MultipartFile file) throws IOException {
        Produit produit1 = new ObjectMapper().readValue(produit, Produit.class);
        if (produit1 != null && file != null) {
            produit1.setPhoto(serviceProduit.uploadImage(file));
        }
        serviceProduit.create(produit1);
    }

    @PutMapping("/update")
    public void updateProduct(@RequestBody Produit produit){

        serviceProduit.update(produit);
    }

    @GetMapping (value = "/image/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImage(@PathVariable Long id) throws IOException {
        return serviceProduit.getImage(id);
    }


}
