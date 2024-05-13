package com.example.JSONPlaceHolderApp.adapter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value ="/test")
@RestController
public class TestController {

    @GetMapping
    public ResponseEntity<String> getAText(){
        return ResponseEntity.ok("ciao");
    }



}
