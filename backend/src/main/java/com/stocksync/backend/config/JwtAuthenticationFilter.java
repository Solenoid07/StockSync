package com.stocksync.backend.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        // 1. Get the "Authorization" header from the request
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        // 2. Check if the header exists and starts with "Bearer "
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response); // If no token, pass it to the next guard (who will reject it)
            return;
        }

        // 3. Extract the Token (Remove "Bearer " prefix)
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt); // Extract username from token

        // 4. If user is found and not yet authenticated...
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // 5. Load user details from Database
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

            // 6. Check if token is valid
            if (jwtService.isTokenValid(jwt, userDetails)) {

                // 7. Create the "Auth Token" (The internal ID card)
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // 8. Set the user as "Authenticated" in the Context
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // 9. Pass the request to the Controller
        filterChain.doFilter(request, response);
    }
}