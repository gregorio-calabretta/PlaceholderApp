package com.example.JSONPlaceHolderApp.port.dto;

import lombok.Data;

@Data
public class DtoRequestToDo {
    private Integer userId;
    private Integer id;
    private String title;
    private boolean completed;
}
