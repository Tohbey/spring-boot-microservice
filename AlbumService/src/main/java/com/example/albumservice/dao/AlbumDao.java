package com.example.albumservice.dao;

import com.example.albumservice.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AlbumDao extends JpaRepository<Album, Long> {
}
