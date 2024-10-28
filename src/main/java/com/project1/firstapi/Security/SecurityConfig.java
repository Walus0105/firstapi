package com.project1.firstapi.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // CSRF
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/rest/products/add").authenticated() // Autoryzacja
                        .anyRequest().authenticated()
                )

                .httpBasic(withDefaults()); //  Domyślna konfiguracja Basic Auth
        return http.build();
    }
}
