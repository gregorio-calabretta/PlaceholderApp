package com.example.JSONPlaceHolderApp.port.service;

import com.example.JSONPlaceHolderApp.adapter.exception.ResourceNotFoundException;
import com.example.JSONPlaceHolderApp.port.dto.DtoRequestAlbum;
import com.example.JSONPlaceHolderApp.port.dto.DtoResponseAlbum;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AlbumService {
    DtoResponseAlbum getAlbumById(Integer id) throws ResourceNotFoundException;

    List<DtoResponseAlbum> getAllAlbums();
    DtoResponseAlbum createAlbum(DtoRequestAlbum dtoRequestAlbum);

    void updateAlbum(DtoRequestAlbum dtoRequestAlbum) throws ResourceNotFoundException;

    void deleteAlbum(Integer id);
}
