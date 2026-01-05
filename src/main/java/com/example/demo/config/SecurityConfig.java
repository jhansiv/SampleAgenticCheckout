package com.example.demo.config;


import com.example.demo.Utils.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
                        .requestMatchers("/auth/**", "/actuator/health").permitAll() // public endpoints
                        .requestMatchers("/checkout-session/**").authenticated()
                        .requestMatchers("/payment/**").authenticated() // your controller
                        .anyRequest().denyAll()                                       // everything else blocked
                )

                // Register your JwtFilter before Spring’s UsernamePasswordAuthenticationFilter
                .addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}

