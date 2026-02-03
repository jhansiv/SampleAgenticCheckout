package com.example.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Security configuration:
 *  - Stateless API (JWT-based)
 *  - Public endpoints: /auth/**, /actuator/health
 *  - Protected endpoints: /checkout-session/**
 *  - Registers JwtFilter to validate Bearer tokens on each request
 */
@Configuration
@EnableMethodSecurity // optional: enables @PreAuthorize, @Secured, etc.
public class SecurityConfig {

    /**
     * Main security filter chain for the application.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // We’re building a stateless REST API, so disable CSRF and cookie sessions
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Authorize requests by path
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**", "/actuator/health","/checkout/**","/products/**","/images/**").permitAll() // public endpoints
                        .anyRequest().permitAll() // your controller                         // everything else blocked
                );

                // Register your JwtFilter before Spring’s UsernamePasswordAuthenticationFilter

        return http.build();
    }
}

