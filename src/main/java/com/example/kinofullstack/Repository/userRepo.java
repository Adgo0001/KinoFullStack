package com.example.kinofullstack.Repository;

import com.example.kinofullstack.Model.user;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepo extends JpaRepository<user, Long> {

    // Find bruger baseret på email
    user findByEmail(String email);

    // Tjek om en bruger eksisterer baseret på email
    boolean existsByEmail(String email);
}

