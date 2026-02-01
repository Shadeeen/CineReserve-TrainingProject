package com.example.finalproject.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@Order(2)
public class SecurityFilter2 implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;


        String path = httpRequest.getRequestURI();

        if (path.equals("/login") || path.startsWith("/swagger") || path.startsWith("/v3") || path.equals("/refresh")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String header = httpRequest.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.getWriter().write("Missing Authorization header");
            return;
        }

        String token = header.substring(7);

        if (!JwtUtil.validateToken(token)) {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.getWriter().write("Invalid token");
            return;
        }

        if (!"ACCESS".equals(JwtUtil.extractType(token))) {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.getWriter().write("Access token required");
            return;
        }

        String role = JwtUtil.extractRole(token);
        String method = httpRequest.getMethod();
        String requestURI = httpRequest.getRequestURI();

        if (((method.equals("POST") || method.equals("PUT") || method.equals("PATCH") || method.equals("DELETE")) && !role.equals("ADMIN")) && (!requestURI.startsWith("/bookings"))) {
            httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            httpResponse.getWriter().write("Access denied: ADMIN only");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }
}
