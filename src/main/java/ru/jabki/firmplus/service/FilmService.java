package ru.jabki.firmplus.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.jabki.firmplus.exception.FilmException;
import ru.jabki.firmplus.model.Film;
import ru.jabki.firmplus.model.Genre;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class FilmService {
    private static Set<Film> films = new HashSet<>();

    public Film addfilm(final Film film) {
        validateFilm(film);
        films.add(film);
        return film;
    }

    public Film getbyId(final Long id) {
        return films.stream().filter(f -> f.getId() == id).findFirst().orElseThrow(() -> new FilmException("Movie not found"));
    }

    public void deleteFilm(final Long id) {
        films.remove(getbyId(id));
    }

    public void updateFilm(final Film film) {
        Film temp = getbyId(film.getId());
        temp.setDescription(film.getDescription());
        temp.setGenres(film.getGenres());
    }

    private void validateFilm(Film film) {
        if (film == null) {
            throw new FilmException("Film is null");
        }
        if (!StringUtils.hasText(film.getName()) || !StringUtils.hasText(film.getDescription())) {
            throw new FilmException("One of the parameters is empty: name - " + film.getName() + " description - " + film.getDescription());
        }
        if (film.getDuration() < 0 || film.getDuration() > 720) {
            throw new FilmException("Duration is too long");
        }
        if (film.getReleaseDate() == null || film.getReleaseDate().isAfter(LocalDate.now())) {
            throw new FilmException("Date is incorrect");
        }
    }

    public List<Film> searchFilm(String name, String description, String duration, LocalDate localdate, Set<Genre> genres) {
        return films.stream().filter(f -> (!StringUtils.hasText(name) || f.getName().toLowerCase().contains(name.toLowerCase())) &&
                (!StringUtils.hasText(description) || f.getDescription().toLowerCase().contains(description.toLowerCase())) &&
                (!StringUtils.hasText(duration) || Objects.equals(f.getDuration(), Long.parseLong(duration))) &&
                (localdate == null || Objects.equals(f.getReleaseDate(), localdate)) &&
                (genres == null || f.getGenres().equals(genres))).toList();
    }
}