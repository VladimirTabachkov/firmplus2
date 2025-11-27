package ru.jabki.firmplus.model;

import lombok.Data;

@Data
public class ApiError {
    final boolean success;
    final String message;
}
