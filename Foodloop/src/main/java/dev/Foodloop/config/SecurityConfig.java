package dev.Foodloop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/users/**").permitAll() // Allow all endpoints under /users without authentication
                        .requestMatchers("/orders/**").permitAll() // Allow all endpoints under /users without authentication
                        .requestMatchers("/fooditems/**").permitAll() // Allow all endpoints under /users without authentication
                        .requestMatchers("/payments/**").permitAll() // Allow all endpoints under /users without authentication
                        .anyRequest().authenticated() // Require authentication for other requests


                )
                .csrf(csrf -> csrf.disable())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
