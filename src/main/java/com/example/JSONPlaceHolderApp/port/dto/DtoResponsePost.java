package com.example.JSONPlaceHolderApp.port.dto;

import lombok.Data;

@Data
public class DtoResponsePost {
    private Integer userId;
    private Integer id;
    private String title;
    private String body;
}
