package com.example.userservice.dto;

import lombok.Data;

@Data
public class LoginRequestModel {
    private String email;
    private String password;
}
