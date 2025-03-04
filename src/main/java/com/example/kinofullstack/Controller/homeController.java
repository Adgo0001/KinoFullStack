package com.example.kinofullstack.Controller;

import com.example.kinofullstack.Service.userService;
import com.example.kinofullstack.Model.user;  // Sørg for, at modellen er korrekt importeret
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class homeController {

    @Autowired
    private userService userService; // Injicer userservice korrekt

    // Startside (peger på index.html i static/login/)
    @GetMapping("/")
    public String showIndexPage() {
        return "redirect:/login/login.html";
    }

    // Viser login-siden
    @GetMapping("/login")
    public String showLoginPage() {
        return "redirect:/login/login.html";
    }

    // Håndterer login
    @PostMapping("/login")
    public String handleLogin(@RequestParam String email,
                              @RequestParam String password) {
        // Tjek om brugeren findes i databasen og om adgangskoden stemmer
        user existingUser = userService.findByEmail(email);

        if (existingUser != null && existingUser.getPassword().equals(password)) {
            return "redirect:/login/administrator.html"; // Login succesfuld
        } else {
            return "redirect:/login?error=Forkert%20brugernavn%20eller%20adgangskode"; // Fejlmeddelelse via query string
        }
    }


    @GetMapping("/signup")
    public String showSignupPage() {
        return "redirect:/login/signup.html"; // Return the signup view (signup.html)
    }

    // Post mapping for handling form submission
    @PostMapping("/signup")
    public String handleSignup(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("confirm-password") String confirmPassword,
            Model model) {

        // Simple validation (check if passwords match)
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match!");
            return "redirect:/login/signup.html"; // Return to signup page with error message
        }

        // Check if the user already exists
        if (userService.userExistsByEmail(email)) {
            model.addAttribute("error", "User already exists!");
            return "redirect:/login/signup.html"; // Return to signup page with error message
        }

        // Create a new user
        user newUser = new user(email, password);
        userService.saveUser(newUser); // Save the user in the database

        // Redirect to login page after successful signup
        return "redirect:/login/login.html"; // Redirect to login page after successful signup
    }
}
