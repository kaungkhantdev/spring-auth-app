package com.auth.app.service;

import com.auth.app.dto.request.UserRequest;
import com.auth.app.model.User;
import com.auth.app.model.Role;
import com.auth.app.repository.RoleRepository;
import com.auth.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User registerUser(UserRequest userDTO) {

        if(userRepository.existsByEmail(userDTO.getEmail())) {
            throw new RuntimeException("Email is already in use. Please choose another one.");
        }

        User user = new User();
        user.setUserName(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFullName());
        user.setLastName(userDTO.getFullName());

        // Set default role (e.g., "USER")
        Role role = roleRepository.findByName("USER")
                .orElseThrow(() -> new IllegalArgumentException("Role USER not found"));
        user.getRoles().add(role);


        return userRepository.save(user);
    }
}
