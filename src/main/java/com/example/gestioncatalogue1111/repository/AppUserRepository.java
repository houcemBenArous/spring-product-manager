package com.example.gestioncatalogue1111.repository;

import com.example.gestioncatalogue1111.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, String> {
    AppUser findByUsername(String username);
}
