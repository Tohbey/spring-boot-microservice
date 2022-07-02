package com.example.userservice.dto;

import lombok.Data;

@Data
public class AlbumDTO {
    private String albumId;
    private String userId;
    private String name;
    private String description;
}
