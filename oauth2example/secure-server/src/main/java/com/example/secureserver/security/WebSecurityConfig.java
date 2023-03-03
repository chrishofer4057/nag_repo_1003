package com.example.secureserver.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Configuration
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .oauth2ResourceServer().jwt();
        return http.build();
    }

    @RestController
    public class RequestController {
        @PreAuthorize("hasAuthority('SCOPE_mod_custom')")
        @GetMapping("/")
        public String getMessage(Principal principal) {
            return "Welcome, " + principal.getName();
        }
    }
}
