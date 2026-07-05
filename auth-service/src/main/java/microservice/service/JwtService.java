package microservice.service;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;


import java.security.Key;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;


@Service
public class JwtService {


    @Value("${jwt.secret}")
    private String secret;


    @Value("${jwt.access-expiration}")
    private Long expiry;


    private Key getKey() {return Keys.hmacShaKeyFor(secret.getBytes());}


    public String generateToken(String username, String role) {
        return Jwts.builder()
                .subject(username)
                .claim("role", role)
                .id(UUID.randomUUID().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiry))
                .signWith(getKey())
                .compact();
    }


    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }


    public String extractJti(String token) {
        return extractClaims(token).getId();
    }

    public LocalDateTime extractExpiration(String token){

        Date date = extractClaims(token).getExpiration();
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

    }

    public boolean isValid(String token) {
        try {
            extractClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }

    }


    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith((javax.crypto.SecretKey) getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }




}