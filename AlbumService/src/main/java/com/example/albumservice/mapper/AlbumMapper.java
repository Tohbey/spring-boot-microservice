package com.example.albumservice.mapper;

import com.example.albumservice.dto.AlbumDTO;
import com.example.albumservice.entity.Album;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AlbumMapper {
    AlbumMapper INSTANCE = Mappers.getMapper(AlbumMapper.class);

    AlbumDTO albumToAlbumDTO(Album album);

    Album albumDTOToAlbum(AlbumDTO albumDTO);
}
