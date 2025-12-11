package ru.jabki.firmplus.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import ru.jabki.firmplus.mapper.ReviewMapper;
import ru.jabki.firmplus.model.Review;

import java.util.List;

@Service
@AllArgsConstructor
public class ReviewRepository {

    private static final String INSERT = """
            INSERT INTO filmplus.review(user_id, movie_id, review_text)
            VALUES (:userId, :movieId, :reviewText)
            RETURNING *;
            """;

    private static final String UPDATE = """
            UPDATE filmplus.review
            SET review_text = :reviewText
            WHERE user_id = :userId
            and movie_id = :movieId
            RETURNING *;
            """;

    private static final String DELETE = """
            DELETE filmplus.review
            WHERE user_id = :user_id
            and movie_id = :movieId
            """;

    private static final String GET_ALL_REVIEW = """
            SELECT *
            FROM filmplus.review
            WHERE movie_id = :movieId
            """;

    private final ReviewMapper reviewMapper;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public Review insert(final Review review) {
        return jdbcTemplate.queryForObject(INSERT, reviewToSql(review), reviewMapper);
    }

    public Review update(final Review review) {
        return jdbcTemplate.queryForObject(UPDATE, reviewToSql(review), reviewMapper);
    }

    public void delete(final Review review) {
        jdbcTemplate.update(DELETE, reviewIdsToSql(review.getUserId(), review.getMovieId()));
    }

    public List<Review> findAll(final Long movieId) {
        return jdbcTemplate.query(GET_ALL_REVIEW,  new MapSqlParameterSource("movieId", movieId), reviewMapper);
    }

    private MapSqlParameterSource reviewToSql(final Review review) {
        final MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("userId", review.getUserId());
        parameterSource.addValue("movieId", review.getMovieId());
        parameterSource.addValue("reviewText", review.getReviewText());
        return parameterSource;
    }

    private MapSqlParameterSource reviewIdsToSql(final Long userId, final Long movieId) {
        final MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("userId", userId);
        parameterSource.addValue("movieId", movieId);
        return parameterSource;
    }
}
