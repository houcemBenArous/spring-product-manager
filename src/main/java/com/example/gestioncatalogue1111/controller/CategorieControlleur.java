package com.example.gestioncatalogue1111.controller;

import com.example.gestioncatalogue1111.entities.Categorie;
import com.example.gestioncatalogue1111.service.impl.CategorieServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@AllArgsConstructor
@CrossOrigin("*")
public class CategorieControlleur {

    private final CategorieServiceImpl categorieService;

    @GetMapping("/all")
    public List<Categorie> getAllCategories() {
        return categorieService.getAll();
    }

    @GetMapping("/{id}")
    public Categorie getCategoryById(@PathVariable String id) {
        return categorieService.getById(id);
    }

    @PostMapping("/add")
    public void addCategory(@RequestBody Categorie categorie) {
        categorieService.create(categorie);
    }

    @PutMapping("/update")
    public void updateCategory(@RequestBody Categorie categorie) {
        categorieService.update(categorie);
    }

    @DeleteMapping("/delete")
    public void deleteCategory(@RequestBody Categorie categorie) {
        categorieService.delete(categorie);
    }
}