package com.example.JSONPlaceHolderApp.adapter.controller;

import com.example.JSONPlaceHolderApp.adapter.exception.ResourceNotFoundException;
import com.example.JSONPlaceHolderApp.port.dto.DtoRequestPost;
import com.example.JSONPlaceHolderApp.port.dto.DtoResponsePost;
import com.example.JSONPlaceHolderApp.port.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value ="/posts")
@RestController

public class PostControllerAdapter {
private final PostService postService;

    public PostControllerAdapter(PostService postService) {
        this.postService = postService;
    }


    @GetMapping(path = "{postId}")
    public ResponseEntity<DtoResponsePost> getAPost(@PathVariable ("postId") Integer id) throws Exception {
        DtoResponsePost dtoResponsePost = postService.getPostById(id);
        return ResponseEntity.status(HttpStatus.OK).body(dtoResponsePost);
    }
    @GetMapping
    public ResponseEntity<List<DtoResponsePost>> getAllPosts(){
        List<DtoResponsePost> dtoResponsePostList = postService.getAllPosts();
        return ResponseEntity.status(HttpStatus.OK).body(dtoResponsePostList);
    }

    @PostMapping
    public ResponseEntity<DtoResponsePost> createPost(@RequestBody DtoRequestPost dtoRequestPost) {
        DtoResponsePost dtoResponsePost = postService.createPost(dtoRequestPost);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoResponsePost);
    }

    @PutMapping
    public ResponseEntity<DtoResponsePost> updatePost(@RequestBody DtoRequestPost dtoRequestPost) throws ResourceNotFoundException {
        postService.updatePost(dtoRequestPost);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping(path = "{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable ("postId") Integer id){
    postService.deletePost(id);
    return ResponseEntity.status(HttpStatus.OK).body(null);
    }



}
