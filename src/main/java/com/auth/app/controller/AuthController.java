package com.auth.app.controller;

import com.auth.app.dto.response.AuthResponse;
import com.auth.app.dto.response.UserResponse;
import com.auth.app.security.JwtTokenProvider;
import com.auth.app.dto.request.AuthRequest;
import com.auth.app.dto.request.UserRequest;
import com.auth.app.model.User;
import com.auth.app.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserRequest userDTO) {
        try {
            User user = authService.registerUser(userDTO);
            return new ResponseEntity<>("Created", HttpStatus.CREATED); // 201 Created
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);  // 400 Bad Request for duplicate email
        }
    }
}
