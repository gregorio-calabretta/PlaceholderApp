package com.example.JSONPlaceHolderApp.port.dto;

import lombok.Data;

@Data
public class DtoResponseComment {
            private Integer postId;
            private Integer id;
            private String name;
            private String email;
            private String body;
}
