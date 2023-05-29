package com.security.jsonwebtoken.auth.rest.model;

import lombok.Data;

@Data
public class LoginRequest {

    private String username;

    private String password;
}
