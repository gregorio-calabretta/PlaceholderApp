package com.example.JSONPlaceHolderApp.port.dto;

import com.example.JSONPlaceHolderApp.adapter.exception.ResourceNotFoundException;
import lombok.Data;

@Data
public class ErrorDto {

 private final String message;

        public ErrorDto(ResourceNotFoundException e){
            message= e.getMessage();
        }
    public String getError() {
        return message;
    }
}
