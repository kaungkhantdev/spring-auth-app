package com.auth.app.controller;

import com.auth.app.model.User;
import com.auth.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @Autowired
    private UserService userService;

    // Accessible to any authenticated user
    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser(@RequestParam String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    // Accessible only to ADMIN or SUPER_ADMIN roles
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
