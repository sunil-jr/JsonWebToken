package com.security.jsonwebtoken.auth.rest.controller;

import com.security.jsonwebtoken.auth.rest.model.LoginRequest;
import com.security.jsonwebtoken.auth.rest.model.LoginResponse;
import com.security.jsonwebtoken.auth.service.AuthService;
import com.security.jsonwebtoken.user.User;
import com.security.jsonwebtoken.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @GetMapping("/current")
    public User getCurrentUser() {
        return authService.getByUsername(JwtUtil.getCurrentUsername());
    }

}
