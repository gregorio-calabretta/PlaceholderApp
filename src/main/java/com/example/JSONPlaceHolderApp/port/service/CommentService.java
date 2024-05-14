package com.example.JSONPlaceHolderApp.port.service;

import com.example.JSONPlaceHolderApp.adapter.exception.ResourceNotFoundException;
import com.example.JSONPlaceHolderApp.port.dto.DtoRequestComment;
import com.example.JSONPlaceHolderApp.port.dto.DtoResponseComment;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CommentService {
    DtoResponseComment getCommentById(Integer id) throws ResourceNotFoundException;

    List<DtoResponseComment> getAllComments();
    DtoResponseComment createComment(DtoRequestComment dtoRequestComment);

    void updateComment(DtoRequestComment dtoRequestComment) throws ResourceNotFoundException;

    void deleteComment(Integer id);

    List<DtoResponseComment> getCommentsByPostId(Integer id);
}
