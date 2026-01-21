
package com.example.demo.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;

public class JwtUtil {
    private static final String SECRET = Arrays.toString(Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded());
    private static final String ISSUER = "agentic-checkout-service";
    private static final Duration EXPIRY = Duration.ofHours(1);

    private static final Key KEY = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    private JwtUtil() {}

    /** Generate a token with subject and optional claims (e.g., roles). */
    public static String generateToken(String subject) {
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(subject)
                .setIssuer(ISSUER)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + EXPIRY.toMillis()))
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    /** Validate and return parsed JWS (claims included). Throws JwtException if invalid. */
    public static Jws<Claims> validate(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(KEY)
                .requireIssuer(ISSUER)
                .setAllowedClockSkewSeconds(60) // allow small skew
                .build()
                .parseClaimsJws(token);
    }

    /** Convenience to extract subject after validation. */
    public static String extractSubject(String token) {
        return validate(token).getBody().getSubject();
    }
}

