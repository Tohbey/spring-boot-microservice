package com.example.albumservice.dto;

import lombok.Data;

@Data
public class AlbumDTO {
    private String albumId;
    private String userId;
    private String name;
    private String description;
}
