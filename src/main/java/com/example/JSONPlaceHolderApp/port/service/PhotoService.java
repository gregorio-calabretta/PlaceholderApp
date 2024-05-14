package com.example.JSONPlaceHolderApp.port.service;

import com.example.JSONPlaceHolderApp.adapter.exception.ResourceNotFoundException;
import com.example.JSONPlaceHolderApp.port.dto.DtoRequestPhoto;
import com.example.JSONPlaceHolderApp.port.dto.DtoResponsePhoto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PhotoService {
    DtoResponsePhoto getPhotoById(Integer id) throws ResourceNotFoundException;

    List<DtoResponsePhoto> getAllPhotos();
    DtoResponsePhoto createPhoto(DtoRequestPhoto dtoRequestPhoto);

    void updatePhoto(DtoRequestPhoto dtoRequestPhoto) throws ResourceNotFoundException;

    void deletePhoto(Integer id);
}
