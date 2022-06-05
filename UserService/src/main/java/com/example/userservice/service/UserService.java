package com.example.userservice.service;

import com.example.userservice.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto userDetails);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    UserDto getUserDetailsByEmail(String email);
    UserDto getUserByUserId(String userId);
}