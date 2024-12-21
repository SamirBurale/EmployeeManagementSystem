package com.employee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable() 
            .authorizeRequests()
            .requestMatchers("/public/**").permitAll()
            .anyRequest().authenticated()        
            .and()
            .httpBasic(); // Enable Basic Authentication
        return http.build();
    }
}