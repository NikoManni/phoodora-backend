package com.phoodora.restapi.security;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;

import com.phoodora.restapi.models.Users;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    
    private AuthenticationManager authenticationManager;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            Users creds = new ObjectMapper().readValue(request.getInputStream(), Users.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(creds.getUsername(), creds.getPassword()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException { 
        String jwtToken = JwtTools.createToken((User)authResult.getPrincipal());
        Map<String, String> jsonBody = new HashMap<>();

        jsonBody.put("access_token", jwtToken);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        new ObjectMapper().writeValue(response.getOutputStream(), jsonBody);
    }
}
