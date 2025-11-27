package ru.jabki.firmplus.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.jabki.firmplus.exception.BadRequestException;
import ru.jabki.firmplus.mapper.FilmMapper;
import ru.jabki.firmplus.model.Film;

@Repository
@AllArgsConstructor
public class FilmRepository {

    private static final String INSERT = """
            INSERT INTO filmplus.movie(name, description, duration, release_date, genres)
            VALUES (:name, :description, :duration, :release_date, :genres)
            RETURNING *;
            """;

    private static final String UPDATE = """
            UPDATE filmplus.movie
            SET name = :name, description = :description, duration = :duration, release_date = :release_date, genres = :genres
            WHERE id = :id
            RETURNING *;
            """;

    private static final String DELETE = """
            DELETE FROM filmplus.movie
            WHERE id = :id;
            """;

    private static final String GET_BY_ID = """
            SELECT * 
            FROM filmplus.movie
            WHERE id = :id;
            """;

    private final FilmMapper filmMapper;
    private NamedParameterJdbcTemplate jdbcTemplate;

    public Film insert(final Film film) {
        return jdbcTemplate.queryForObject(INSERT, filmToSql(film), filmMapper);
    }

    public Film update (final Film film) {
        return jdbcTemplate.queryForObject(UPDATE, filmToSql(film), filmMapper);
    }

    public void delete(final long id) {
        try {
            jdbcTemplate.update(DELETE, new MapSqlParameterSource("id", id));
        } catch (Exception e) {
            throw new BadRequestException(String.format("Film id = %d not found", id));
        }
    }

    public Film findById(final long id) {
        try {
            return jdbcTemplate.queryForObject(GET_BY_ID, new MapSqlParameterSource("id", id), filmMapper);
        } catch (Exception e) {
            throw new BadRequestException(String.format("Film id = %d not found", id));
        }
    }

    private MapSqlParameterSource filmToSql(final Film film) {
        final MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", film.getId());
        params.addValue("name", film.getName());
        params.addValue("description", film.getDescription());
        params.addValue("release_date", film.getReleaseDate());
        params.addValue("duration", film.getDuration());
        params.addValue(
                "genres",
                film.getGenres().stream()
                        .map(Enum::name)
                        .toArray(String[]::new)
        );
        return params;
    }
}