package ru.jabki.firmplus.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.jabki.firmplus.model.Film;
import ru.jabki.firmplus.service.FilmService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/film")
@Tag(name = "Фильм")
public class FilmController {
    private final FilmService filmService;

    @PostMapping
    @Operation(summary = "Создать фильм")
    public Film create(@RequestBody final Film film) {
        return filmService.create(film);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить данные фильма")
    public Film getById(@PathVariable("id") String id) {
        return filmService.getById(Long.parseLong(id));
    }

    @PatchMapping
    @Operation(summary = "Обновить описание или жанры для фильма")
    public void update(@RequestBody final Film film) {
        filmService.update(film);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить фильм")
    public void delete(@PathVariable("id") Long id) {
        filmService.delete(id);
    }
}
