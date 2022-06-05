package com.example.userservice.dto;

import lombok.Data;

@Data
public class UserResponse {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
}
