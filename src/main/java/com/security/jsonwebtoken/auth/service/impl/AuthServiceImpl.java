package com.security.jsonwebtoken.auth.service.impl;

import com.security.jsonwebtoken.auth.rest.model.LoginRequest;
import com.security.jsonwebtoken.auth.rest.model.LoginResponse;
import com.security.jsonwebtoken.auth.service.AuthService;
import com.security.jsonwebtoken.user.User;
import com.security.jsonwebtoken.user.UserRepository;
import com.security.jsonwebtoken.util.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthServiceImpl implements AuthService {

    @Value("${jwt.secretKey}")
    private String secretKey;

    @Value("${jwt.expiryInMinutes}")
    private Integer expiryInMinutes;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private String authenticateUser(String username, String password) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username or password not valid!!"));
        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new BadCredentialsException("Username or password not valid!!");
        return JwtUtil.generateToken(username, new Date(System.currentTimeMillis() + expiryInMinutes * 60 * 1000), secretKey);
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        String token = authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());
        return new LoginResponse(token);
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username).orElse(new User());
    }

}
