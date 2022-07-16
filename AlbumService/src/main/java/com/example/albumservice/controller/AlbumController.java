package com.example.albumservice.controller;

import com.example.albumservice.dto.AlbumDTO;
import com.example.albumservice.dto.ResponseObject;
import com.example.albumservice.entity.Album;
import com.example.albumservice.service.AlbumService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping(AlbumController.BASE_URL)
public class AlbumController {

    @Autowired
    Environment environment;

    private final AlbumService albumService;
    public static final String BASE_URL = "/api/v1/album";


    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/status/check")
    public String status(){
        return "Working on port "+environment.getProperty("server.port");
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ResponseObject<AlbumDTO>> getAllAlbums(){
        ResponseObject<AlbumDTO> object = new ResponseObject<>();
        try {
            List<AlbumDTO> albumDTOList = albumService.getAlbums();
            object.setData(albumDTOList);
            object.setValid(true);
            object.setMessage("Resource Retrieved Successfully");
        }catch (Exception e) {
            object.setValid(false);
            object.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(object);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/user/{userId}")
    public ResponseEntity<ResponseObject<AlbumDTO>> getAllAlbumsByUser(@PathVariable ("userId")String userId){
        ResponseObject<AlbumDTO> object = new ResponseObject<>();
        try {
            List<AlbumDTO> albumDTOList = albumService.getAlbumsByUser(userId);
            object.setData(albumDTOList);
            object.setValid(true);
            object.setMessage("Resource Retrieved Successfully");
        }catch (Exception e) {
            object.setValid(false);
            object.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(object);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/{albumId}")
    public ResponseEntity<ResponseObject<AlbumDTO>> getAlbum(@PathVariable ("albumId")Long albumId){
        ResponseObject<AlbumDTO> object = new ResponseObject<>();
        try {
            AlbumDTO album = albumService.getAlbum(albumId);
            object.setData(Collections.singletonList(album));
            object.setValid(true);
            object.setMessage("Resource Retrieved Successfully");
        }catch (Exception e) {
            object.setValid(false);
            object.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(object);
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/{albumId}")
    public ResponseEntity<ResponseObject<String>> deleteAlbum(@PathVariable ("albumId")Long albumId){
        ResponseObject<String> object = new ResponseObject<>();
        try {
            albumService.deleteAlbum(albumId);
            object.setValid(true);
            object.setMessage("Resource Deleted Successfully");
        }catch (Exception e) {
            object.setValid(false);
            object.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(object);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ResponseObject<AlbumDTO>> createAlbum(@RequestBody Album album){
        ResponseObject<AlbumDTO> object = new ResponseObject<>();
        try {
            AlbumDTO albumDTO = albumService.createAlbum(album);
            object.setData(Collections.singletonList(albumDTO));
            object.setValid(true);
            object.setMessage("Resource Created Successfully");
        }catch (Exception e) {
            object.setValid(false);
            object.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(object);
    }
}
