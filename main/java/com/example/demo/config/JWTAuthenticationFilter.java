package com.example.demo.config;

import jakarta.annotation.security.RolesAllowed;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Role;
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
//Authentication 
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) 
                                    throws ServletException, IOException {
                               final String authHeader = request.getHeader("Authorization");
                               final  String JWT , UserEmail;
                               if(authHeader == null || !authHeader.startsWith("Bearer ")) {
                                   filterChain.doFilter(request, response);
                                   return;
                               }
                               JWT = authHeader.substring(7);
                               UserEmail = jwtService.extractUsername(JWT);
                               if(UserEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                                   UserDetails userDetails = this.userDetailsService.loadUserByUsername(UserEmail);
                                   if(jwtService.isTokenValid(JWT, userDetails)) {
                                       UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                                       authToken.setDetails(new WebAuthenticationDetailsSource()
                                               .buildDetails(request));
                                       SecurityContextHolder.getContext().setAuthentication(authToken);
                                   }
                               }
                               filterChain.doFilter(request, response);
    }
}
