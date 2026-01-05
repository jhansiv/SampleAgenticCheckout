
package com.example.demo.Utils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

/**
 * JWT authentication filter:
 *  - Reads Authorization: Bearer <token>
 *  - Validates the token via JwtUtil (or JwtService)
 *  - Sets Authentication in SecurityContext
 *  - Skips public endpoints
 */
public class JwtFilter extends OncePerRequestFilter {

    private static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    protected boolean shouldNotFilter(@NonNull HttpServletRequest request) {
        String path = request.getServletPath();
        // Adjust these to your needs
        return PATH_MATCHER.match("/auth/**", path)
                || PATH_MATCHER.match("/actuator/health", path)
                || PATH_MATCHER.match("/public/**", path);
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            try {
                String username = JwtUtil.extractSubject(token); // or validateToken(token)
                List<String> roles = List.of("ROLE_USER"); // default or extracted

                var auth = new UsernamePasswordAuthenticationToken(
                        username, /* principal */
                        null,     /* credentials */
                        roles.stream().map(SimpleGrantedAuthority::new).toList()
                );

                SecurityContextHolder.getContext().setAuthentication(auth);

            } catch (Exception e) {
                // Invalid/expired token → 401
                SecurityContextHolder.clearContext();
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
