package com.example.springbootdemo.controller;

import com.example.springbootdemo.model.User;
import com.example.springbootdemo.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.servlet.http.HttpSession;


@Controller
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService, HttpSession session) {
        this.authService = authService;
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(password.getBytes());
        return Base64.getEncoder().encodeToString(hash);
    }

    @PostMapping("/auth/login")
    public String login(@RequestParam("login") String login,
                       @RequestParam("password") String password) throws NoSuchAlgorithmException {
        User foundUser = authService.findByLogin(login);
        if (foundUser == null) {
            return "redirect:/auth";
        }
        String hashedPassword = hashPassword(password);

        if (!hashedPassword.equals(foundUser.getPassword())) {
            return "redirect:/auth";
        }

        authService.getSession().setAttribute("user", foundUser);
        return "redirect:/records";
    }

    @GetMapping("/auth")
    public String openAuthPage() {
        return "auth";
    }



}
