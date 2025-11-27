package ru.jabki.firmplus.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.jabki.firmplus.model.ApiError;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleError(RuntimeException exception) {
        return ResponseEntity.badRequest().body(new ApiError(false, exception.getMessage()));
    }
}