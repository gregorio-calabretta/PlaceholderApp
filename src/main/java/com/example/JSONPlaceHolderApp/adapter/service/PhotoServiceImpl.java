package com.example.JSONPlaceHolderApp.adapter.service;

import com.example.JSONPlaceHolderApp.adapter.exception.ResourceNotFoundException;
import com.example.JSONPlaceHolderApp.port.dto.DtoRequestPhoto;
import com.example.JSONPlaceHolderApp.port.dto.DtoResponsePhoto;
import com.example.JSONPlaceHolderApp.port.service.PhotoService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;
@Service
public class PhotoServiceImpl implements PhotoService {
    private final RestClient restClient = RestClient.create();
    private final String uriBase = "https://jsonplaceholder.typicode.com";

    @Override
    public DtoResponsePhoto getPhotoById(Integer id) throws ResourceNotFoundException {
        try{
            return restClient.get()
                    .uri(uriBase + "/photos/" + id)
                    .accept(APPLICATION_JSON)
                    .retrieve()
                    .body(DtoResponsePhoto.class);
        }catch(HttpClientErrorException.NotFound httpClientErrorException) {
            throw new ResourceNotFoundException("Photo not found for id: " + id);
        }
    }

    @Override
    public List<DtoResponsePhoto> getAllPhotos() {
        return restClient.get()
                .uri(uriBase + "/photos")
                .accept(APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    @Override
    public DtoResponsePhoto createPhoto(DtoRequestPhoto dtoRequestPhoto) {
        return restClient.post()
                .uri(uriBase + "/photos")
                .contentType(APPLICATION_JSON)
                .body(dtoRequestPhoto)
                .retrieve()
                .toEntity(DtoResponsePhoto.class).getBody();
    }

    @Override
    public void updatePhoto(DtoRequestPhoto dtoRequestPhoto) throws ResourceNotFoundException {
        DtoResponsePhoto photo = restClient.get()
                .uri(uriBase + "/photos/" + dtoRequestPhoto.getId())
                .accept(APPLICATION_JSON)
                .retrieve()
                .body(DtoResponsePhoto.class);

        if(photo == null) throw new ResourceNotFoundException("Photo not found for id: " + dtoRequestPhoto.getId());

        else {
            restClient.put()
                    .uri(uriBase + "/photos/" + dtoRequestPhoto.getId())
                    .contentType(APPLICATION_JSON)
                    .body(photo)
                    .retrieve()
                    .toBodilessEntity();
        }
    }

    @Override
    public void deletePhoto(Integer id) {
        restClient.delete()
                .uri(uriBase + "/photos/" + id)
                .retrieve()
                .toBodilessEntity();
    }
}
