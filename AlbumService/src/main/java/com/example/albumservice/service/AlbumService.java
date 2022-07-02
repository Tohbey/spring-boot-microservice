package com.example.albumservice.service;

import com.example.albumservice.dto.AlbumDTO;
import com.example.albumservice.entity.Album;

import java.util.List;
import java.util.Optional;

public interface AlbumService {
    List<AlbumDTO> getAlbumsByUser(String userId);

    List<AlbumDTO> getAlbums();

    AlbumDTO createAlbum(Album album) throws Exception;

    void deleteAlbum(long albumId);

    AlbumDTO getAlbum(Long albumId);
}
