package com.rating.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public JwtDecoder jwtDecoder() {

        // Use a JWK endpoint to configure the JwtDecoder
        return NimbusJwtDecoder.withJwkSetUri("https://dev-57137902.okta.com/oauth2/default/v1/keys").build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .authorizeHttpRequests(request -> request.anyRequest().authenticated())
                .oauth2ResourceServer(authServer -> authServer.jwt(Customizer.withDefaults()));

        return httpSecurity.build();
    }
}
