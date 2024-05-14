package com.example.JSONPlaceHolderApp.adapter.controller;

import com.example.JSONPlaceHolderApp.adapter.exception.ResourceNotFoundException;
import com.example.JSONPlaceHolderApp.port.dto.DtoRequestAlbum;
import com.example.JSONPlaceHolderApp.port.dto.DtoResponseAlbum;
import com.example.JSONPlaceHolderApp.port.service.AlbumService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping(value ="/albums")
@RestController
public class AlbumControllerAdapter {

    public final AlbumService albumService;

    public AlbumControllerAdapter(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping(path = "{albumId}")
    public ResponseEntity<DtoResponseAlbum> getAAlbum(@PathVariable("albumId") Integer id) throws Exception {
        DtoResponseAlbum dtoResponseAlbum = albumService.getAlbumById(id);
        return ResponseEntity.status(HttpStatus.OK).body(dtoResponseAlbum);
    }


    @GetMapping
    public ResponseEntity<List<DtoResponseAlbum>> getAllAlbums(){
        List<DtoResponseAlbum> dtoResponseAlbumList = albumService.getAllAlbums();
        return ResponseEntity.status(HttpStatus.OK).body(dtoResponseAlbumList);
    }

    @PostMapping
    public ResponseEntity<DtoResponseAlbum> createAlbum(@RequestBody DtoRequestAlbum dtoRequestAlbum) {
        DtoResponseAlbum dtoResponseAlbum = albumService.createAlbum(dtoRequestAlbum);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoResponseAlbum);
    }


    @PutMapping
    public ResponseEntity<DtoResponseAlbum> updatePost(@RequestBody DtoRequestAlbum dtoRequestAlbum) throws ResourceNotFoundException {
        albumService.updateAlbum(dtoRequestAlbum);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping(path = "{albumId}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable ("albumId") Integer id){
        albumService.deleteAlbum(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    
}
