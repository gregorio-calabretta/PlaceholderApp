package com.example.JSONPlaceHolderApp.port.dto;

import lombok.Data;

@Data
public class DtoResponsePhoto {
    private Integer albumId;
    private Integer  id;
    private String title;
    private String url;
    private String thumbnailUrl;
}
