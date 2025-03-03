package com.auth.app.config;

import com.auth.app.model.User;
import com.auth.app.model.Role;
import com.auth.app.model.Permission;
import com.auth.app.repository.UserRepository;
import com.auth.app.repository.RoleRepository;
import com.auth.app.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class DataSeeder {

    @Bean
    public CommandLineRunner seedDatabase(UserRepository userRepo,
                                          RoleRepository roleRepo,
                                          PermissionRepository permissionRepo,
                                          PasswordEncoder passwordEncoder,
                                          @Value("${seed.data.enabled:false}") boolean seedDataEnabled) {
        return args -> {
            if (!seedDataEnabled) {
                System.out.println("⚠️ Data seeding is disabled. Skipping...");
                return;
            }

            // Seed Permissions
            String[] permissions = {"PERMISSION_READ", "PERMISSION_WRITE", "PERMISSION_DELETE"};
            for (String permissionName : permissions) {
                permissionRepo.findByName(permissionName).orElseGet(() -> {
                    Permission p = new Permission();
                    p.setName(permissionName);
                    return permissionRepo.save(p);
                });
            }

            // Seed Roles
            Role adminRole = roleRepo.findByName("ADMIN").orElseGet(() -> {
                Role r = new Role();
                r.setName("ADMIN");
                r.setPermissions(Set.copyOf(permissionRepo.findAll()));
                return roleRepo.save(r);
            });

            Role userRole = roleRepo.findByName("USER").orElseGet(() -> {
                Role r = new Role();
                r.setName("USER");
                r.setPermissions(Set.of(permissionRepo.findByName("PERMISSION_READ").get()));
                return roleRepo.save(r);
            });

            User admin = userRepo.findByEmail("admin@example.com");
            if (admin == null) {
                admin = new User();
                admin.setEmail("admin@example.com");
                admin.setUserName("admin");
                admin.setFirstName("Admin");
                admin.setLastName("User");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRoles(Set.of(adminRole));
                userRepo.save(admin);
            }

            User user = userRepo.findByEmail("user@example.com");
            if (user == null) {
                user = new User();
                user.setEmail("user@example.com");
                user.setUserName("user");
                user.setFirstName("Normal");
                user.setLastName("User");
                user.setPassword(passwordEncoder.encode("user123"));
                user.setRoles(Set.of(userRole));
                userRepo.save(user);
            }

            System.out.println("✅ Seed data inserted successfully!");
        };
    }
}
