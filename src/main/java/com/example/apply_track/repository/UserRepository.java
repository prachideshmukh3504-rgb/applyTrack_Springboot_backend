package com.example.apply_track.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.apply_track.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find user using email
    Optional<User> findByEmail(String email);

    // Check whether email already exists
    boolean existsByEmail(String email);

}