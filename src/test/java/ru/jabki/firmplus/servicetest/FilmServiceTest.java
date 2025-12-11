package ru.jabki.firmplus.servicetest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.jabki.firmplus.model.Film;
import ru.jabki.firmplus.model.Genre;
import ru.jabki.firmplus.repository.FilmRepository;
import ru.jabki.firmplus.service.FilmService;

import java.time.LocalDate;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FilmServiceTest {
    @Mock
    private FilmRepository filmRepository;

    @InjectMocks
    private FilmService filmService;

    @Test
    void testCreateFilm_valid() {
        final Film film = getFilm();
        when(filmRepository.insert(film)).thenReturn(film);
        Film result = filmService.create(film);
        assertThat(result).isEqualTo(film);
        verify(filmRepository).insert(film);
    }

    @Test
    void testUpdateFilm_valid() {
        final Film film = getFilm();
        Film updatedFromDb = Film.builder()
                .id(film.getId())
                .name("Хищник: Планета смерти")
                .description("Изгнанный из клана хищник Дек отправляется на опасную планету Генна.")
                .releaseDate(LocalDate.of(2025, 11, 5))
                .duration(107L)
                .genres(Set.of(Genre.FANTASY, Genre.COMEDY, Genre.ACTION))
                .build();

        when(filmRepository.update(film)).thenReturn(updatedFromDb);
        Film result = filmService.update(film);
        assertThat(result.getName()).isEqualTo("Хищник: Планета смерти");
        assertThat(result.getDescription()).isEqualTo("Изгнанный из клана хищник Дек отправляется на опасную планету Генна.");
        assertThat(result.getReleaseDate()).isEqualTo(LocalDate.of(2025, 11, 5));
        assertThat(result.getDuration()).isEqualTo(107L);
        assertThat(result.getGenres()).isEqualTo(Set.of(Genre.FANTASY, Genre.COMEDY, Genre.ACTION));
        verify(filmRepository).update(film);
    }

    @Test
    void testGetFilm_valid() {
        final Film film = getFilm();
        when(filmRepository.findById(film.getId())).thenReturn(film);
        Film result = filmService.getById(film.getId());
        assertThat(result).isEqualTo(film);
        verify(filmRepository).findById(film.getId());
    }

    @Test
    void testDeleteFilm_valid() {
        long id = 1L;
        doNothing().when(filmRepository).delete(id);
        filmService.delete(id);
        verify(filmRepository).delete(id);
    }

    private Film getFilm() {
        return Film
                .builder()
                .id(1L)
                .name("Франкенштейн")
                .description("1857 год. Команда застрявшего в северных льдах датского корабля спасает раненого мужчину от похожего на человека чудовища")
                .releaseDate(LocalDate.of(2025, 9, 30))
                .duration(150L)
                .genres(Set.of(Genre.HORROR, Genre.ACTION))
                .build();
    }
}
