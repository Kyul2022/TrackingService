package com.example.trackee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(authz -> authz
	                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()  // <-- Add this line
	            .requestMatchers("/api/tracking/**").permitAll()  // <-- public endpoint
	            .anyRequest().authenticated()                  // <-- everything else secured
	        )
	        .oauth2ResourceServer(oauth2 -> oauth2
	            .jwt(jwt -> {})
	        );
	    return http.build();
	}
}