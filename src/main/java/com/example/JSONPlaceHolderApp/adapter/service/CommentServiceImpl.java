package com.example.JSONPlaceHolderApp.adapter.service;

import com.example.JSONPlaceHolderApp.adapter.exception.ResourceNotFoundException;
import com.example.JSONPlaceHolderApp.port.dto.DtoRequestComment;
import com.example.JSONPlaceHolderApp.port.dto.DtoResponseComment;
import com.example.JSONPlaceHolderApp.port.service.CommentService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;
@Service
public class CommentServiceImpl implements CommentService {
    private final RestClient restClient = RestClient.create();
    private final String uriBase = "https://jsonplaceholder.typicode.com";

    @Override
    public DtoResponseComment getCommentById(Integer id) throws ResourceNotFoundException {
        try{
            return restClient.get()
                    .uri(uriBase + "/comments/" + id)
                    .accept(APPLICATION_JSON)
                    .retrieve()
                    .body(DtoResponseComment.class);
        }catch(HttpClientErrorException.NotFound httpClientErrorException) {
            throw new ResourceNotFoundException("Comment not found for id: " + id);
        }
    }

    @Override
    public List<DtoResponseComment> getAllComments() {
        return restClient.get()
                .uri(uriBase + "/comments")
                .accept(APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    @Override
    public DtoResponseComment createComment(DtoRequestComment dtoRequestComment) {
        return restClient.post()
                .uri(uriBase + "/comments")
                .contentType(APPLICATION_JSON)
                .body(dtoRequestComment)
                .retrieve()
                .toEntity(DtoResponseComment.class).getBody();
    }

    @Override
    public void updateComment(DtoRequestComment dtoRequestComment) throws ResourceNotFoundException {
        DtoResponseComment comment = restClient.get()
                .uri(uriBase + "/comments/" + dtoRequestComment.getId())
                .accept(APPLICATION_JSON)
                .retrieve()
                .body(DtoResponseComment.class);

        if(comment == null) throw new ResourceNotFoundException("Comment not found for id: " + dtoRequestComment.getId());

        else {
            restClient.put()
                    .uri(uriBase + "/comments/" + dtoRequestComment.getId())
                    .contentType(APPLICATION_JSON)
                    .body(comment)
                    .retrieve()
                    .toBodilessEntity();
        }
    }

    @Override
    public void deleteComment(Integer id) {
                restClient.delete()
                .uri(uriBase + "/comments/" + id)
                .retrieve()
                .toBodilessEntity();
    }

    @Override
    public List<DtoResponseComment> getCommentsByPostId(Integer id) {
        return restClient.get()
                .uri(uriBase + "/posts/"+ id + "/comments")
                .accept(APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }
}
