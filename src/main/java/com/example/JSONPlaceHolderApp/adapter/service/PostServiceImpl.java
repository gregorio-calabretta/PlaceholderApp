package com.example.JSONPlaceHolderApp.adapter.service;

import com.example.JSONPlaceHolderApp.adapter.exception.ResourceNotFoundException;
import com.example.JSONPlaceHolderApp.port.dto.DtoRequestPost;
import com.example.JSONPlaceHolderApp.port.dto.DtoResponsePost;
import com.example.JSONPlaceHolderApp.port.service.PostService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
public class PostServiceImpl implements PostService {

    private final RestClient restClient = RestClient.create();
    private final String uriBase = "https://jsonplaceholder.typicode.com";


    @Override
    public DtoResponsePost getPostById(Integer id) throws ResourceNotFoundException {
        try{
        return restClient.get()
            .uri(uriBase + "/posts/" + id)
            .accept(APPLICATION_JSON)
            .retrieve()
            .body(DtoResponsePost.class);
        }catch(HttpClientErrorException.NotFound httpClientErrorException) {
             throw new ResourceNotFoundException("Post not found for id: " + id);
        }
    }


    @Override
    public List<DtoResponsePost> getAllPosts(){
        return restClient.get()
                .uri(uriBase + "/posts")
                .accept(APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    @Override
    public DtoResponsePost createPost(DtoRequestPost dtoRequestPost) {
         return restClient.post()
                .uri(uriBase + "/posts")
                .contentType(APPLICATION_JSON)
                .body(dtoRequestPost)
                .retrieve()
                .toEntity(DtoResponsePost.class).getBody();
    }

    @Override
    public void updatePost(DtoRequestPost dtoRequestPost) throws ResourceNotFoundException {
        DtoResponsePost post = restClient.get()
                .uri(uriBase + "/posts/" + dtoRequestPost.getId())
                .accept(APPLICATION_JSON)
                .retrieve()
                .body(DtoResponsePost.class);

        if(post == null) throw new ResourceNotFoundException("Post not found for id: " + dtoRequestPost.getId());

        else {
            restClient.put()
                    .uri(uriBase + "/posts/" + dtoRequestPost.getId())
                    .contentType(APPLICATION_JSON)
                    .body(post)
                    .retrieve()
                    .toBodilessEntity();
        }

    }

    @Override
    public void deletePost(Integer id) {
                restClient.delete()
                .uri(uriBase + "/posts/" + id)
                .retrieve()
                .toBodilessEntity();
    }


}

