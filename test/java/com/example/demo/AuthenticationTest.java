package com.example.demo;

import com.example.demo.Controller.AuthenticationResponse;
import com.example.demo.Controller.LogIn;
import com.example.demo.Entity.Role;
import com.example.demo.Entity.User;
import com.example.demo.Repositary.UserRepo;
import com.example.demo.Service.AuthenticationService;
import com.example.demo.config.JwtService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
// # Used for Intialization of Mock object
public class AuthenticationTest {
    @Mock
    private UserRepo userRepo;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtService jwtService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private AuthenticationService authenticationService;
    @Test
    public void testRegisterUser_AdminRole() {
        User request = new User();
        request.setId(1L);
        request.set_Role(Role.ADMIN);
        request.setEmail("admin@gmail.com");
        request.setPassword("password");
        request.setName("admin");
        when(passwordEncoder.encode(request.getPassword())).thenReturn("password");
        when(jwtService.generateToken(Mockito.any(User.class))).thenReturn("JwtToken");
        AuthenticationResponse response = authenticationService.registr(request);
        verify(userRepo).save(Mockito.any(User.class));
        verify(jwtService).generateToken(Mockito.any(User.class));
        assertNotNull(response);
        assertEquals("ADMIN SignedUp Sucessfully" , response.getToken());
    }
    @Test
    public void loginUser(){
        LogIn request = new LogIn();
        request.setEmail("test@example.com");
        request.setPassword("password");

        User mockUser = new User();
        mockUser.setEmail("test@example.com");

        String mockJwtToken = "mock-jwt-token";

        // Mock dependencies
        when(userRepo.findByEmail(request.getEmail())).thenReturn(Optional.of(mockUser));
        when(jwtService.generateToken(mockUser)).thenReturn(mockJwtToken);

        // Act
        AuthenticationResponse response = authenticationService.authenticate(request);

        // Assert
        verify(authenticationManager).authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        verify(userRepo).findByEmail(request.getEmail());
        verify(jwtService).generateToken(mockUser);

        assertNotNull(response);
        assertEquals(mockJwtToken, response.getToken());
    }
}
