package com.example.userservice.dto;

import lombok.Data;

@Data
public class AuthenticationResponse {
    private final String jwt;
}
