package com.example.blogapi.controller;

import com.example.blogapi.entity.User;
import com.example.blogapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.blogapi.payload.UserDto;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepo;

    // ✅ Update username by ID
    @PutMapping("/{id}/username")
    public ResponseEntity<User> updateUsername(@PathVariable Long id, @RequestBody Map<String, String> body) {
        String newUsername = body.get("username");

        return userRepo.findById(id).map(user -> {
            user.setUsername(newUsername);
            userRepo.save(user);
            return ResponseEntity.ok(user);
        }).orElse(ResponseEntity.notFound().build());
    }

    // ✅ Get all users (for debugging / Postman testing)
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepo.findAll());
    }
    @PostMapping("/signup")
public ResponseEntity<?> registerUser(@RequestBody User user) {
    // 1. Validate email format (basic)
    if (!user.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
        return ResponseEntity.badRequest().body("Invalid email format");
    }

    // 2. Check if email already exists
    if (userRepo.findByEmail(user.getEmail()).isPresent()) {
        return ResponseEntity.status(409).body("Email already in use");
    }

    // 3. Save user
    User savedUser = userRepo.save(user);
    return ResponseEntity.status(201).body(savedUser);
}
@PostMapping("/login")
public ResponseEntity<?> loginUser(@RequestBody Map<String, String> credentials) {
    String email = credentials.get("email");
    String password = credentials.get("password");

    return userRepo.findByEmail(email)
            .filter(user -> user.getPassword().equals(password))
            .<ResponseEntity<?>>map(ResponseEntity::ok) // generic fix
            .orElse(ResponseEntity.status(401).body("Invalid email or password"));
}
// ✅ Get single user by ID (for profile page)
@GetMapping("/{id}")
public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
    return userRepo.findById(id)
            .map(user -> {
                UserDto dto = new UserDto(user.getId(), user.getUsername(), user.getEmail());
                return ResponseEntity.ok(dto);
            })
            .orElse(ResponseEntity.notFound().build());
}
}
