package com.auth.app.repository;

import com.auth.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserName(String userName);
    User findByEmail(String email);
    boolean existsByEmail(String email);
}
