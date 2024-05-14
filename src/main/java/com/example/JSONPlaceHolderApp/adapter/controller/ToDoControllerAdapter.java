package com.example.JSONPlaceHolderApp.adapter.controller;

import com.example.JSONPlaceHolderApp.adapter.exception.ResourceNotFoundException;
import com.example.JSONPlaceHolderApp.port.dto.DtoRequestToDo;
import com.example.JSONPlaceHolderApp.port.dto.DtoResponseToDo;
import com.example.JSONPlaceHolderApp.port.service.ToDoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping(value = "/todos")
@RestController
public class ToDoControllerAdapter {

    public final ToDoService toDoService;

    public ToDoControllerAdapter(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping(path = "{todoId}")
    public ResponseEntity<DtoResponseToDo> getAToDo(@PathVariable("todoId") Integer id) throws Exception {
        DtoResponseToDo dtoResponseToDo = toDoService.getToDoById(id);
        return ResponseEntity.status(HttpStatus.OK).body(dtoResponseToDo);
    }


    @GetMapping
    public ResponseEntity<List<DtoResponseToDo>> getAllToDos(){
        List<DtoResponseToDo> dtoResponseToDoList = toDoService.getAllToDos();
        return ResponseEntity.status(HttpStatus.OK).body(dtoResponseToDoList);
    }

    @PostMapping
    public ResponseEntity<DtoResponseToDo> createToDo(@RequestBody DtoRequestToDo dtoRequestToDo) {
        DtoResponseToDo dtoResponseToDo = toDoService.createToDo(dtoRequestToDo);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoResponseToDo);
    }


    @PutMapping
    public ResponseEntity<DtoResponseToDo> updatePost(@RequestBody DtoRequestToDo dtoRequestToDo) throws ResourceNotFoundException {
        toDoService.updateToDo(dtoRequestToDo);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping(path = "{todoId}")
    public ResponseEntity<Void> deleteToDo(@PathVariable ("todoId") Integer id){
        toDoService.deleteToDo(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
