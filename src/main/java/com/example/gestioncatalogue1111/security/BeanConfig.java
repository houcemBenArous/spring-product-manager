package com.example.gestioncatalogue1111.security;

import com.example.gestioncatalogue1111.entities.AppUser;
import com.example.gestioncatalogue1111.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
public class BeanConfig {
    private final AppUserRepository appUserRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return  new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                AppUser appUser = appUserRepository.findByUsername(username);
                if (appUser == null) {
                    return null;
                }
                return new User(appUser.getUsername(),appUser.getPassword(),
                        appUser.getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getNom())).toList());
            }
        };
    }

    @Bean
    public AuthentificationProvider authentificationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenficationManager authenficationManager(AuthentificationConfiguration authentificationConfiguration) throws Exception{
        return  authentificationConfiguration.getAuthentificationManager();
    }
}
