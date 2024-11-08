package com.talhaguller.todolist.service;

import com.talhaguller.todolist.config.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    @Autowired
    private JwtConfig jwtConfig;

    // Token üretme
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpiration()))
                .signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret())
                .compact();
    }

    // Token'dan claim bilgilerini çıkarma
    public Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtConfig.getSecret())
                .parseClaimsJws(token)
                .getBody();
    }

    // Token geçerli mi kontrolü (UserDetails ile)
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractClaims(token).getSubject();
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // Token geçerli mi kontrolü (UserDetails olmadan)
    public boolean isTokenValid(String token) {
        try {
            extractClaims(token); // Tokeni çözümleyerek geçerliliğini kontrol eder
            return true;
        } catch (ExpiredJwtException | SignatureException e) {
            System.out.println("Invalid or expired JWT token: " + e.getMessage());
            return false;
        }
    }

    // Token süresi dolmuş mu kontrolü
    private boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }
}
