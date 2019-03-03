package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http.authorizeExchange()
                .pathMatchers("/", "/home", "/favicon.ico").permitAll()
                .anyExchange().authenticated()
                .and().httpBasic()
                .and().build();
    }

    @Bean
    public ReactiveUserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .passwordEncoder(passwordEncoder()::encode)
                .username("user")
                .password("password")
                .roles("USER")
                .build();
        return new MapReactiveUserDetailsService(user);
    }
}
