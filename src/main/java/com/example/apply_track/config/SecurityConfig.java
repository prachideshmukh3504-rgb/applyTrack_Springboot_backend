package com.example.apply_track.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
            // Disable CSRF for our REST API
            .csrf(AbstractHttpConfigurer::disable)

            // Configure API authorization
            .authorizeHttpRequests(auth -> auth

                // Allow user registration without authentication
                .requestMatchers(
                    HttpMethod.POST,
                    "/api/auth/register"
                ).permitAll()

                // Require authentication for all other APIs
                .anyRequest().authenticated()
            );

        return http.build();
    }
}
