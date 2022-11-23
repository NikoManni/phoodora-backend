package com.phoodora.restapi.security;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetails userDetailsService;

    @Autowired
    BCryptPasswordEncoder bcrypt;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bcrypt);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        http.csrf().disable();
        http.cors()
            .and()
            .authorizeRequests()
            .antMatchers("/").permitAll()
            .antMatchers("/restaurants/**").permitAll()
            .antMatchers("/register/**").permitAll()
            .antMatchers("/login").permitAll()
            .antMatchers("/customer/**").hasRole("CUSTOMER")
            .antMatchers("/admin/**").hasRole("MANAGER")
            .anyRequest()
            .authenticated()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilter(new AuthenticationFilter(authenticationManager()));
        http.addFilter(new AuthorizationFilter(authenticationManager()));
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() 
    {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "https://phoodora-frontend.herokuapp.com"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","PUT","OPTIONS","PATCH"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
