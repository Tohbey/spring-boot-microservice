package com.example.albumservice.dao;

import com.example.albumservice.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AlbumDao extends JpaRepository<Album, Long> {
    Optional<Album> findByName(String name);

    List<Album> findByUserId(String id);
}
