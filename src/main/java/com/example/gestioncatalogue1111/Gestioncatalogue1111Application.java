package com.example.gestioncatalogue1111;

import com.example.gestioncatalogue1111.entities.AppRole;
import com.example.gestioncatalogue1111.entities.AppUser;
import com.example.gestioncatalogue1111.entities.Categorie;
import com.example.gestioncatalogue1111.entities.Produit;
import com.example.gestioncatalogue1111.repository.AppRoleRepository;
import com.example.gestioncatalogue1111.repository.AppUserRepository;
import com.example.gestioncatalogue1111.repository.CategorieRepository;
import com.example.gestioncatalogue1111.repository.ProduitRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class Gestioncatalogue1111Application {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    CommandLineRunner runner(AppRoleRepository roleRepository, AppUserRepository appUserRepository){
        return args -> {

            AppRole appRole1 = AppRole.builder().nom("ADMIN").build();
            AppRole appRole2 = AppRole.builder().nom("USER").build();

            roleRepository.save(appRole1);
            roleRepository.save(appRole2);

            appUserRepository.save(AppUser.builder()
                    .id(UUID.randomUUID().toString())
                    .username("mohamed")
                    .mail("mohamed@gmail.com")
                    .password(passwordEncoder().encode("1234"))
                    .roles((List.of(appRole1, appRole2)))
                    .build()
            );


            appUserRepository.save(AppUser.builder()
                    .id(UUID.randomUUID().toString())
                    .username("ahmed")
                    .mail("ahmed@gmail.com")
                    .password(passwordEncoder().encode("1234"))
                    .roles((List.of(appRole1, appRole2)))
                    .build()
            );


        };
    }
    public static void main(String[] args) {
        SpringApplication.run(Gestioncatalogue1111Application.class, args);
    }

}
