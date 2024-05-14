package com.example.JSONPlaceHolderApp.adapter.controller;

import com.example.JSONPlaceHolderApp.adapter.exception.ResourceNotFoundException;
import com.example.JSONPlaceHolderApp.port.dto.DtoRequestComment;
import com.example.JSONPlaceHolderApp.port.dto.DtoRequestPost;
import com.example.JSONPlaceHolderApp.port.dto.DtoResponseComment;
import com.example.JSONPlaceHolderApp.port.dto.DtoResponsePost;
import com.example.JSONPlaceHolderApp.port.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping(path = "/")
@RestController
public class CommentControllerAdapter {

private final CommentService commentService;

    public CommentControllerAdapter(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping(path = "comments"+ "{commentId}")
    public ResponseEntity<DtoResponseComment> getAComment(@PathVariable("commentId") Integer id) throws Exception {
        DtoResponseComment dtoResponseComment = commentService.getCommentById(id);
        return ResponseEntity.status(HttpStatus.OK).body(dtoResponseComment);
    }


    @GetMapping(path = "comments")
    public ResponseEntity<List<DtoResponseComment>> getAllComments(){
        List<DtoResponseComment> dtoResponseCommentList = commentService.getAllComments();
        return ResponseEntity.status(HttpStatus.OK).body(dtoResponseCommentList);
    }

    @PostMapping(path = "comments")
    public ResponseEntity<DtoResponseComment> createComment(@RequestBody DtoRequestComment dtoRequestComment) {
        DtoResponseComment dtoResponseComment = commentService.createComment(dtoRequestComment);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoResponseComment);
    }


    @PutMapping(path = "comments")
    public ResponseEntity<DtoResponseComment> updatePost(@RequestBody DtoRequestComment dtoRequestComment) throws ResourceNotFoundException {
        commentService.updateComment(dtoRequestComment);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping(path = "comments"+ "{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable ("commentId") Integer id){
        commentService.deleteComment(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping(path = "posts/"+ "{postId}"+"/comments")
    public ResponseEntity<List<DtoResponseComment>> getCommentsByPostId(@RequestParam("postId") Integer id) throws Exception {
        List<DtoResponseComment> dtoResponseCommentList = commentService.getCommentsByPostId(id);
        return ResponseEntity.status(HttpStatus.OK).body(dtoResponseCommentList);
    }

}
