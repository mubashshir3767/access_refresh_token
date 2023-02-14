package com.example.refresh_access_token.filter;

import com.example.refresh_access_token.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestHeader = request.getHeader("Authorization");
        if (requestHeader == null || !requestHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = requestHeader.replace("Bearer ", "");
        Claims claims = JwtUtils.isValidAccessToken(token);
        if (claims == null) {
            filterChain.doFilter(request, response);
            return;
        }
        List<String> authorities = JwtUtils.getAuthorities(claims);
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        claims.getSubject(),
                        null,
                        authorities(authorities)
                );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    private List<SimpleGrantedAuthority> authorities(List<String> authorities) {
        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        authorities.forEach((role) -> {
            roles.add(new SimpleGrantedAuthority(role));
        });

        return roles;
    }
}
