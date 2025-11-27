package ru.jabki.firmplus.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Review {
    private Long id;
    private final Long userId;
    private final Long movieId;
    private String reviewText;
    private LocalDate createdAt;
}