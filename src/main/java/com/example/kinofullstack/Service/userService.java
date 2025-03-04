package com.example.kinofullstack.Service;

import com.example.kinofullstack.Model.user;
import com.example.kinofullstack.Repository.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userService {

    @Autowired
    private userRepo userRepo; // Injicer userrepo korrekt

    // Tjek om brugeren findes baseret på email
    public boolean userExistsByEmail(String email) {
        return userRepo.findByEmail(email) != null; // Find bruger via email
    }

    // Gem en ny bruger
    public void saveUser(user newUser) {
        userRepo.save(newUser); // Gem brugeren i databasen via repository
    }

    // Find en bruger efter email
    public user findByEmail(String email) {
        return userRepo.findByEmail(email); // Find bruger via email
    }
}
