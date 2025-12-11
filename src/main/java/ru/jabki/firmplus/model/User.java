package ru.jabki.firmplus.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class User {
    private Long id;
    private String login;
    private String name;
    private String email;
    private LocalDate birthday;
}