package com.example.userservice.service;

import com.example.userservice.dto.AuthenticationRequest;
import com.example.userservice.dto.AuthenticationResponse;
import com.example.userservice.entity.User;

import java.util.Optional;

public interface AuthenticationService {

    AuthenticationResponse createAuthenticationToken(AuthenticationRequest authenticationRequest) throws Exception;

    Optional<User> getCurrentUser();
}
