package com.example.userservice.service;

import com.example.userservice.dto.AlbumResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "album-ws")
public interface AlbumServiceClient {
    @GetMapping("/users/{id}/nalbums")
    AlbumResponse getAlbums(@PathVariable Long id);
}
