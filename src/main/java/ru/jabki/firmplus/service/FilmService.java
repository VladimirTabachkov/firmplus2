package ru.jabki.firmplus.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.jabki.firmplus.exception.FilmException;
import ru.jabki.firmplus.exception.UserException;
import ru.jabki.firmplus.model.Film;
import ru.jabki.firmplus.repository.FilmRepository;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class FilmService {
    private final FilmRepository filmRepository;

    @Transactional(rollbackFor = Exception.class)
    public Film create(final Film film) {
        validate(film);
        return filmRepository.insert(film);
    }

    @Transactional(readOnly = true)
    public Film getById(final Long id) {
        final Film film = filmRepository.findById(id);
        if (film == null) {
            throw new UserException("Film not found");
        }
        return film;
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(final Long id) {
        filmRepository.delete(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public Film update(final Film film) {
        validate(film);
        final Film existFilm = getById(film.getId());
        existFilm.setName(film.getName());
        existFilm.setDescription(film.getDescription());
        existFilm.setReleaseDate(film.getReleaseDate());
        existFilm.setDuration(film.getDuration());
        existFilm.setGenres(film.getGenres());
        return filmRepository.update(existFilm);
    }

    private void validate(Film film) {
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
}