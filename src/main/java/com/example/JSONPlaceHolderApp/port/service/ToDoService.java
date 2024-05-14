package com.example.JSONPlaceHolderApp.port.service;

import com.example.JSONPlaceHolderApp.adapter.exception.ResourceNotFoundException;
import com.example.JSONPlaceHolderApp.port.dto.DtoRequestToDo;
import com.example.JSONPlaceHolderApp.port.dto.DtoResponseToDo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ToDoService {
    DtoResponseToDo getToDoById(Integer id) throws ResourceNotFoundException;

    List<DtoResponseToDo> getAllToDos();
    DtoResponseToDo createToDo(DtoRequestToDo dtoRequestToDo);

    void updateToDo(DtoRequestToDo dtoRequestToDo) throws ResourceNotFoundException;

    void deleteToDo(Integer id);
}
