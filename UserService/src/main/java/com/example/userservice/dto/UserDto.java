package com.example.userservice.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -953297098295050686L;

    private String fullName;
    private String userUrl;
    private String email;
    private String password;
    private String userId;
    private String encryptedPassword;
}