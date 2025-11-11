package com.example.gestioncatalogue1111.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity
@AllArgsConstructor //assurer l'injection de dépandance
public class SecurityConfig {
    private authentificationProvider authentificationProvider;
    private jwtAuthentificationFilter jwtAuthentificationFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) //on va utiliser jwt
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // authentification staless
                .authorizeHttpRequests(request -> request.requestMatchers("/api/login").permitAll().anyRequest().authenticated()) // utilisateur ne peut acceder qu'à l'interface login sans authentifier
                .authenticationProvider(authentificationProvider) //n'est pas necessaire
                .addFilterBefore(jwtAuthentificationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
