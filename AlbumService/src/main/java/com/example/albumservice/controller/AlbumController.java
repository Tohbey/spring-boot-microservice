package com.example.albumservice.controller;

import com.example.albumservice.service.AlbumService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(AlbumController.BASE_URL)
public class AlbumController {

    @Autowired
    private Environment environment;


    private final AlbumService albumService;
    public static final String BASE_URL = "/api/v1/album";


    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }
}
