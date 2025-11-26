package ru.jabki.firmplus.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.jabki.firmplus.model.Film;
import ru.jabki.firmplus.model.Genre;
import ru.jabki.firmplus.service.FilmService;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/film")
@Tag(name = "Фильм")
public class FilmController {

    private final FilmService filmLogic;

    public FilmController(final FilmService films) {
        this.filmLogic = films;
    }

    @PostMapping
    @Operation(summary = "Создать фильм")
    public Film create(@RequestBody final Film film) {
        return filmLogic.addfilm(film);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить данные фильма")
    public Film getById(@PathVariable("id") String id) {
        return filmLogic.getbyId(Long.parseLong(id));
    }

    @PatchMapping
    @Operation(summary = "Обновить описание или жанры для фильма")
    public void update(@RequestBody final Film film) {
        filmLogic.updateFilm(film);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить фильм")
    public void delete(@PathVariable("id") Long id) {
        filmLogic.deleteFilm(id);
    }

    @GetMapping("/search")
    @Operation(summary = "Поиск фильма")
    public List<Film> searchFilm(@RequestParam(required = false) String name,
                                 @RequestParam(required = false) String description,
                                 @RequestParam(required = false) LocalDate releaseDate,
                                 @RequestParam(required = false) String duration,
                                 @RequestParam(required = false) Set<Genre> genres) {
        return filmLogic.searchFilm(name, description, duration, releaseDate, genres);
    }
}
