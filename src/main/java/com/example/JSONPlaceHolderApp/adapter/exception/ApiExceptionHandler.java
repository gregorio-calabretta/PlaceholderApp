package com.example.JSONPlaceHolderApp.adapter.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.server.ServerWebExchange;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
        @ExceptionHandler(value = { ResourceNotFoundException.class })
        @ResponseStatus(HttpStatus.NO_CONTENT)
        protected ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException ex, ServerWebExchange request) {
            return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NO_CONTENT, request).block();
        }





}
