package ru.jabki.firmplus.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.jabki.firmplus.exception.BadRequestException;
import ru.jabki.firmplus.mapper.LikeMapper;
import ru.jabki.firmplus.model.Like;

import java.util.List;

@Repository
@AllArgsConstructor
public class LikeRepository {

    private static final String INSERT = """
            INSERT INTO filmplus.like (user_id, movie_id)
            VALUES (:userId, :movieId)
            RETURNING *;
            """;

    private static final String DELETE = """
            DELETE filmplus.like
            WHERE user_id = :userId
            and movie_id = :movieId
            """;

    private static final String GET_ALL_LIKE = """
            SELECT *
            FROM filmplus.like
            WHERE movie_id = :movieId
            """;

    private final LikeMapper likeMapper;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public Like insert(final Like like) {
        return jdbcTemplate.queryForObject(INSERT, likeToSql(like), likeMapper);
    }

    public void delete(final Like like) {
        try {
            jdbcTemplate.queryForObject(DELETE, likeToSql(like), likeMapper);
        } catch (Exception e) {
            throw new BadRequestException(String.format("Movie id = %d and User id = %d not found ", like.getMovieId(), like.getUserId()));
        }
    }

    public List<Like> findAll(final long movieId) {
        try {
            return jdbcTemplate.query(GET_ALL_LIKE, new MapSqlParameterSource("movieId", movieId), likeMapper);
        } catch (Exception e) {
            throw new BadRequestException(String.format("User id = %d not found", movieId));
        }
    }

    private MapSqlParameterSource likeToSql(final Like like) {
        final MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("userId", like.getUserId());
        parameterSource.addValue("movieId", like.getMovieId());
        return parameterSource;
    }
}
