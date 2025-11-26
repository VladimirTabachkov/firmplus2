package ru.jabki.firmplus.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.jabki.firmplus.exception.FilmException;
import ru.jabki.firmplus.exception.UserException;
import ru.jabki.firmplus.model.ApiError;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler({UserException.class, FilmException.class})
    public ResponseEntity<ApiError> handleError(RuntimeException exception) {
        return ResponseEntity.badRequest().body(new ApiError(false, exception.getMessage()));
    }
}