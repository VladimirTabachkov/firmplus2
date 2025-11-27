package ru.jabki.firmplus.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Like {
    private final Long userId;
    private final Long movieId;
}