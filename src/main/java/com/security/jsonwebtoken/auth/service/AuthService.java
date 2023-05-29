package com.security.jsonwebtoken.auth.service;

import com.security.jsonwebtoken.auth.rest.model.LoginRequest;
import com.security.jsonwebtoken.auth.rest.model.LoginResponse;
import com.security.jsonwebtoken.user.User;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);

    User getByUsername(String username);
}
