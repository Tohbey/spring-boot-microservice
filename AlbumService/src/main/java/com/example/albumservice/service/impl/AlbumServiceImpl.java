package com.example.albumservice.service.impl;

import com.example.albumservice.dao.AlbumDao;
import com.example.albumservice.dto.AlbumDTO;
import com.example.albumservice.entity.Album;
import com.example.albumservice.exceptions.NotFoundException;
import com.example.albumservice.mapper.AlbumMapper;
import com.example.albumservice.service.AlbumService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlbumServiceImpl implements AlbumService {

    private AlbumMapper albumMapper;
    AlbumDao albumDao;

    public AlbumServiceImpl(AlbumMapper albumMapper, AlbumDao albumDao){
        this.albumMapper = albumMapper;
        this.albumDao = albumDao;
    }

    @Override
    public List<AlbumDTO> getAlbumsByUser(String userId) {
        List<Album> albums = this.albumDao.findByUserId(userId);

        return albums.stream().map(
                album -> {
                    AlbumDTO albumDTO = albumMapper.albumToAlbumDTO(album);
                    return albumDTO;
                }
        ).collect(Collectors.toList());
    }

    @Override
    public List<AlbumDTO> getAlbums() {
        List<Album> albums = this.albumDao.findAll();
        return albums.stream().map(
                album -> {
                    AlbumDTO albumDTO = albumMapper.albumToAlbumDTO(album);
                    return albumDTO;
                }
        ).collect(Collectors.toList());
    }

    @Override
    public AlbumDTO createAlbum(Album album) throws Exception {
        Optional<Album> checkAlbum = this.albumDao.findByName(album.getName());
        if (checkAlbum.isPresent()) {
            throw new Exception("A user with this email already exist " + checkAlbum.get().getName());
        }
        return saveAndReturnDTO(album);
    }

    private AlbumDTO saveAndReturnDTO(Album album) {
        Album savedAlbum = this.albumDao.save(album);

        AlbumDTO albumDTO = albumMapper.albumToAlbumDTO(savedAlbum);

        return albumDTO;
    }

    @Override
    public void deleteAlbum(long albumId) {
        this.albumDao.deleteById(albumId);
    }

    @Override
    public AlbumDTO getAlbum(Long albumId) {
        Optional<Album> album = this.albumDao.findById(albumId);
        if (album.isEmpty()) {
            throw new NotFoundException("User Not Found. for ID value " + albumId);
        }

        AlbumDTO albumDTO = albumMapper.albumToAlbumDTO(album.get());
        return albumDTO;
    }
}
