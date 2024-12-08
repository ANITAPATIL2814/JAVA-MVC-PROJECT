package com.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.demo.serviceimpl.CustomerServiceImpl;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final CustomerServiceImpl customerService;
    private final PasswordEncoder passwordEncoder;  // Injecting PasswordEncoder

    // Constructor injection for CustomerServiceImpl and PasswordEncoder
    public WebSecurityConfig(CustomerServiceImpl customerService, PasswordEncoder passwordEncoder) {
        this.customerService = customerService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .requestMatchers("/customer/register", "/customer/login").permitAll()  // Allow public access to registration and login
                .anyRequest().authenticated()  // All other requests require authentication
            .and()
            .formLogin()
                .loginPage("/customer/login")  // Custom login page
                .defaultSuccessUrl("/products/add", true)  // Redirect to dashboard after successful login
                .permitAll()  // Allow all users to access login page
            .and()
            .logout()
                .permitAll();  // Allow all users to log out
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
            .userDetailsService(customerService)  // Use CustomerService for userDetails
            .passwordEncoder(passwordEncoder);  // Use PasswordEncoder injected from SecurityConfig
        return authenticationManagerBuilder.build();
    }
}
