package com.talhaguller.todolist.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {


    private JwtUtil jwtUtil;

    private UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");

        String token = null;
        String username = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);

            try {
                // Tokeni çıkar ve doğrula
                if (jwtUtil.isTokenValid(token)) { // isTokenValid token geçerliliğini doğrulayan bir metod olmalı
                    username = jwtUtil.extractClaims(token).getSubject();
                }
            } catch (Exception e) {
                // Hatalı token yakalanırsa log veya hata yönetimi yapılabilir
                System.out.println("Invalid JWT token: " + e.getMessage());
            }
        }

        // Kullanıcı adı boş değil ve kimlik doğrulama durumu boşsa kimlik doğrulama işlemi yapılır
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // Token tekrar doğrulanır
            if (jwtUtil.isTokenValid(token, userDetails)) { // Kullanıcı detaylarına göre token doğrulaması yapılabilir
                var authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        // Filtre zincirine devam et
        filterChain.doFilter(request, response);
    }

}
