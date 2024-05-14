package com.example.JSONPlaceHolderApp.port.service;

import com.example.JSONPlaceHolderApp.adapter.exception.ResourceNotFoundException;
import com.example.JSONPlaceHolderApp.port.dto.DtoRequestPost;
import com.example.JSONPlaceHolderApp.port.dto.DtoResponsePost;

import java.util.List;

@org.springframework.stereotype.Service
public interface PostService {
    DtoResponsePost getPostById(Integer id) throws ResourceNotFoundException;

    List<DtoResponsePost> getAllPosts();
    DtoResponsePost createPost(DtoRequestPost dtoRequestPost);

    void updatePost(DtoRequestPost dtoRequestPost) throws ResourceNotFoundException;

    void deletePost(Integer id);
}
