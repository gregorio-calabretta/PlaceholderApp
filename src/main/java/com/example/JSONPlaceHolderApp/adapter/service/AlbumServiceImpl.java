package com.example.JSONPlaceHolderApp.adapter.service;

import com.example.JSONPlaceHolderApp.adapter.exception.ResourceNotFoundException;
import com.example.JSONPlaceHolderApp.port.dto.DtoRequestAlbum;
import com.example.JSONPlaceHolderApp.port.dto.DtoResponseAlbum;
import com.example.JSONPlaceHolderApp.port.service.AlbumService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
public class AlbumServiceImpl implements AlbumService {
    private final RestClient restClient = RestClient.create();
    private final String uriBase = "https://jsonplaceholder.typicode.com";

    @Override
    public DtoResponseAlbum getAlbumById(Integer id) throws ResourceNotFoundException {
        try{
            return restClient.get()
                    .uri(uriBase + "/albums/" + id)
                    .accept(APPLICATION_JSON)
                    .retrieve()
                    .body(DtoResponseAlbum.class);
        }catch(HttpClientErrorException.NotFound httpClientErrorException) {
            throw new ResourceNotFoundException("Album not found for id: " + id);
        }
    }

    @Override
    public List<DtoResponseAlbum> getAllAlbums() {
        return restClient.get()
                .uri(uriBase + "/albums")
                .accept(APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    @Override
    public DtoResponseAlbum createAlbum(DtoRequestAlbum dtoRequestAlbum) {
        return restClient.post()
                .uri(uriBase + "/albums")
                .contentType(APPLICATION_JSON)
                .body(dtoRequestAlbum)
                .retrieve()
                .toEntity(DtoResponseAlbum.class).getBody();
    }

    @Override
    public void updateAlbum(DtoRequestAlbum dtoRequestAlbum) throws ResourceNotFoundException {
        DtoResponseAlbum album = restClient.get()
                .uri(uriBase + "/albums/" + dtoRequestAlbum.getId())
                .accept(APPLICATION_JSON)
                .retrieve()
                .body(DtoResponseAlbum.class);

        if(album == null) throw new ResourceNotFoundException("Album not found for id: " + dtoRequestAlbum.getId());

        else {
            restClient.put()
                    .uri(uriBase + "/albums/" + dtoRequestAlbum.getId())
                    .contentType(APPLICATION_JSON)
                    .body(album)
                    .retrieve()
                    .toBodilessEntity();
        }
    }

    @Override
    public void deleteAlbum(Integer id) {
        restClient.delete()
                .uri(uriBase + "/albums/" + id)
                .retrieve()
                .toBodilessEntity();
    }
}
