package ru.jabki.firmplus.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.jabki.firmplus.model.Review;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ReviewMapper implements RowMapper<Review> {

    @Override
    public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Review
                .builder()
                .id(rs.getLong("id"))
                .userId(rs.getLong("user_id"))
                .movieId(rs.getLong("movie_id"))
                .reviewText(rs.getString("review_text"))
                .createdAt(rs.getDate("created_at").toLocalDate())
                .build();
    }
}
