package com.example.JSONPlaceHolderApp.adapter.controller;

import com.example.JSONPlaceHolderApp.adapter.exception.ResourceNotFoundException;
import com.example.JSONPlaceHolderApp.port.dto.DtoRequestPhoto;
import com.example.JSONPlaceHolderApp.port.dto.DtoResponsePhoto;
import com.example.JSONPlaceHolderApp.port.service.PhotoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping(value ="/photos")
@RestController
public class PhotoControllerAdapter {

        public final PhotoService photoService;

        public PhotoControllerAdapter(PhotoService photoService) {
            this.photoService = photoService;
        }

        @GetMapping(path = "{photoId}")
        public ResponseEntity<DtoResponsePhoto> getAPhoto(@PathVariable("photoId") Integer id) throws Exception {
            DtoResponsePhoto dtoResponsePhoto = photoService.getPhotoById(id);
            return ResponseEntity.status(HttpStatus.OK).body(dtoResponsePhoto);
        }


        @GetMapping
        public ResponseEntity<List<DtoResponsePhoto>> getAllPhotos(){
            List<DtoResponsePhoto> dtoResponsePhotoList = photoService.getAllPhotos();
            return ResponseEntity.status(HttpStatus.OK).body(dtoResponsePhotoList);
        }

        @PostMapping
        public ResponseEntity<DtoResponsePhoto> createPhoto(@RequestBody DtoRequestPhoto dtoRequestPhoto) {
            DtoResponsePhoto dtoResponsePhoto = photoService.createPhoto(dtoRequestPhoto);
            return ResponseEntity.status(HttpStatus.CREATED).body(dtoResponsePhoto);
        }


        @PutMapping
        public ResponseEntity<DtoResponsePhoto> updatePost(@RequestBody DtoRequestPhoto dtoRequestPhoto) throws ResourceNotFoundException {
            photoService.updatePhoto(dtoRequestPhoto);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }

        @DeleteMapping(path = "{photoId}")
        public ResponseEntity<Void> deletePhoto(@PathVariable ("photoId") Integer id){
            photoService.deletePhoto(id);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
}
