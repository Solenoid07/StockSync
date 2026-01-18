package com.stocksync.backend.config;

import com.stocksync.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UserRepository userRepository;

    // 1. The "User Finder" Tool
    // Spring asks: "How do I find a user named 'yuvi'?"
    // We answer: "Use userRepository.findByUsername()"
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    // 2. The "Auth Provider" (The Logic)
    // It combines the UserFinder and the PasswordEncoder to check credentials.
    @Bean
    public AuthenticationProvider authenticationProvider() {
        // ✅ FIX: Use empty constructor, then set the service separately.
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService()); // 👈 MOVED HERE

        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    // 3. The "Manager" (The Boss)
    // This is the object we actually call in our code to say "Authenticate him!"
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // 4. The "Password Encoder"
    // We never store passwords as plain text (e.g., "1234").
    // We store them as messy hashes (e.g., "$2a$10$XyZ..."). BCrypt does that.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}