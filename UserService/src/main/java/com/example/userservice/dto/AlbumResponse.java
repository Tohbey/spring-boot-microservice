package com.example.userservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class AlbumResponse {
    private Boolean valid;
    private String message;
    private List<AlbumDTO> data;
}
