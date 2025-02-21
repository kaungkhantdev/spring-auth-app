package com.auth.app.controller;

import com.auth.app.security.JwtTokenProvider;
import com.auth.app.dto.AuthRequest;
import com.auth.app.dto.AuthResponse;
import com.auth.app.dto.UserDTO;
import com.auth.app.model.User;
import com.auth.app.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticateUser(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(), authRequest.getPassword()
                )
        );
        String token = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody UserDTO userDTO) {
        User user = authService.registerUser(userDTO);
        return ResponseEntity.ok(user);
    }
}
