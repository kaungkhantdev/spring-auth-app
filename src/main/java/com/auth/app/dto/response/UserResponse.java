package com.auth.app.dto.response;

import com.auth.app.model.Role;
import com.auth.app.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserResponse {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private List<String> roles;

    // ✅ Ensure you have a default constructor (important for Jackson)
    public UserResponse() {}

    public UserResponse(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.roles = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toList());
    }

    // ✅ Add Getters
    public Long getId() { return id; }
    public String getEmail() { return email; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public List<String> getRoles() { return roles; }

}
