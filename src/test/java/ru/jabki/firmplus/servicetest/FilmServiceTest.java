package ru.jabki.firmplus.servicetest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.jabki.firmplus.model.Film;
import ru.jabki.firmplus.model.Genre;
import ru.jabki.firmplus.service.FilmService;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilmServiceTest {
    private FilmService filmService;

    @BeforeEach
    void setUp() {
        filmService = new FilmService();

        filmService.addfilm(
                new Film(1L,
                       "Хищник: Планета смерти",
                   "Изгнанный из клана хищник Дек отправляется на опасную планету Генна.",
                             LocalDate.of(2025, 11, 5),
                     107L,
                             new HashSet<>(Set.of(Genre.FANTASY, Genre.ACTION))
        ));
        filmService.addfilm(
                new Film(2L,
                       "Франкенштейн",
                   "1857 год. Команда застрявшего в северных льдах датского корабля спасает раненого мужчину от похожего на человека чудовища",
                     LocalDate.of(2025, 9, 30),
                     150L,
                             new HashSet<>(Set.of(Genre.DRAMA, Genre.FANTASY))
        ));
    }

    @Test
    void testCreate() {
        Film film = filmService.addfilm(
                new Film(3L,
                "Зверополис 2",
                "Кролик-полицейский Джуди и лис Ник идут по следу загадочной рептилии, чьё прибытие в Зверополис переворачивает жизнь города с ног на голову",
                LocalDate.of(2025, 11, 26),
                107L,
                new HashSet<>(Set.of(Genre.ANIMATED, Genre.COMEDY))
        ));
        assertEquals(3L, film.getId());
        assertEquals("Зверополис 2", film.getName());
        assertEquals(LocalDate.of(2025, 11, 26), film.getReleaseDate());
        assertEquals(107, film.getDuration());
        assertTrue(film.getGenres().contains(Genre.ANIMATED));
        assertTrue(film.getGenres().contains(Genre.COMEDY));
    }
}
