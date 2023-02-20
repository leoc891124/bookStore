package com.weCode.bookStore.config;

import com.weCode.bookStore.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {


    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String tokenWithBearer = request.getHeader("Authorization");

        if(tokenWithBearer ==null || !tokenWithBearer.startsWith("Bearer")){

            filterChain.doFilter(request,response);
            return;
        }

        String token = tokenWithBearer.substring(7);

        Authentication authentication = jwtUtil.validateToken(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);

    }
}
