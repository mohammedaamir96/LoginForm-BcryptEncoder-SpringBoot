package com.example.formlogin.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.formlogin.pojo.UserRegistrationPOJO;
import com.example.formlogin.repo.UserRepository;
import com.example.formlogin.service.UserService;

import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepo;

    @ModelAttribute
    public void commonUser(Principal p, Model m) {
        if (p != null) {
            String username = p.getName();
            UserRegistrationPOJO user = userRepo.findByUsername(username);
            m.addAttribute("user", user);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserRegistrationPOJO user) {
    	boolean userValue = userService.getUser(user);
        if (userValue != false) {
            return ResponseEntity.ok("User is authenticated");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not authenticated");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegistrationPOJO user, HttpSession session, Model m) {
    	if (user.getPassword() == null || user.getPassword().isEmpty()) {
            // Handle null or empty password
            session.setAttribute("msg", "Password cannot be null or empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something wrong check input"); // Return an error view or redirect
        }
    	UserRegistrationPOJO u = userService.saveUser(user);
        if (u != null) {
            session.setAttribute("msg", "Register successfully");
            return ResponseEntity.ok("Register successfully");
        } else {
            session.setAttribute("msg", "Something wrong server");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something wrong server");
        }
    }
    
    @PostMapping("/home")
    public String home(@RequestBody UserRegistrationPOJO user) {
		return "Welcome Home";
    	
    }
}
