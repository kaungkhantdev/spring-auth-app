package com.auth.app.service;

import com.auth.app.dto.response.UserResponse;
import com.auth.app.model.User;
import com.auth.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUserName(username);
    }
}
