package com.example.apply_track.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.apply_track.dto.RegisterRequest;
import com.example.apply_track.dto.RegisterResponse;
import com.example.apply_track.entity.User;
import com.example.apply_track.exception.EmailAlreadyExistsException;
import com.example.apply_track.repository.UserRepository;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Constructor injection
    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // User registration
    public RegisterResponse registerUser(RegisterRequest request) {

        // Check whether email already exists
    	if (userRepository.existsByEmail(request.getEmail().trim())) {
    	    throw new EmailAlreadyExistsException("Email is already registered");
    	}

        // Create new user
        User user = new User();

        user.setName(request.getName().trim());
        user.setEmail(request.getEmail().trim().toLowerCase());

        // Encrypt password before saving
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // Save user into database
        User savedUser = userRepository.save(user);

        // Return response without password
        return new RegisterResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                "User registered successfully"
        );
    }
}
