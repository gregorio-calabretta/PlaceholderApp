package com.example.JSONPlaceHolderApp.adapter.service;

import com.example.JSONPlaceHolderApp.adapter.exception.ResourceNotFoundException;
import com.example.JSONPlaceHolderApp.port.dto.DtoRequestToDo;
import com.example.JSONPlaceHolderApp.port.dto.DtoResponseToDo;
import com.example.JSONPlaceHolderApp.port.service.ToDoService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;
@Service
public class ToDoServiceImpl implements ToDoService {
    private final RestClient restClient = RestClient.create();
    private final String uriBase = "https://jsonplaceholder.typicode.com";

    @Override
    public DtoResponseToDo getToDoById(Integer id) throws ResourceNotFoundException {
        try{
            return restClient.get()
                    .uri(uriBase + "/todos/" + id)
                    .accept(APPLICATION_JSON)
                    .retrieve()
                    .body(DtoResponseToDo.class);
        }catch(HttpClientErrorException.NotFound httpClientErrorException) {
            throw new ResourceNotFoundException("Todo not found for id: " + id);
        }
    }

    @Override
    public List<DtoResponseToDo> getAllToDos() {
        return restClient.get()
                .uri(uriBase + "/todos")
                .accept(APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    @Override
    public DtoResponseToDo createToDo(DtoRequestToDo dtoRequestToDo) {
        return restClient.post()
                .uri(uriBase + "/todos")
                .contentType(APPLICATION_JSON)
                .body(dtoRequestToDo)
                .retrieve()
                .toEntity(DtoResponseToDo.class).getBody();
    }

    @Override
    public void updateToDo(DtoRequestToDo dtoRequestToDo) throws ResourceNotFoundException {
        DtoResponseToDo toDo = restClient.get()
                .uri(uriBase + "/todos/" + dtoRequestToDo.getId())
                .accept(APPLICATION_JSON)
                .retrieve()
                .body(DtoResponseToDo.class);

        if(toDo == null) throw new ResourceNotFoundException("Todo not found for id: " + dtoRequestToDo.getId());

        else {
            restClient.put()
                    .uri(uriBase + "/todos/" + dtoRequestToDo.getId())
                    .contentType(APPLICATION_JSON)
                    .body(toDo)
                    .retrieve()
                    .toBodilessEntity();
        }
    }

    @Override
    public void deleteToDo(Integer id) {
        restClient.delete()
                .uri(uriBase + "/todos/" + id)
                .retrieve()
                .toBodilessEntity();
    }
}
