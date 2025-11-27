package ru.jabki.firmplus.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.jabki.firmplus.exception.BadRequestException;
import ru.jabki.firmplus.mapper.FriendMapper;
import ru.jabki.firmplus.model.Friend;

import java.util.List;

@Repository
@AllArgsConstructor
public class FriendRepository {

    private static final String INSERT = """
        INSERT INTO filmplus.friend(user_id, friend_id)
        VALUES (:userId, :friendId)
        RETURNING *;
    """;

    private static final String DELETE = """
        DELETE FROM filmplus.friend
        WHERE (user_id = :userId) AND (friend_id = :friendId);
        """;

    private static final String GET_ALL_FRIEND = """
        SELECT * 
        FROM filmplus.friend
        WHERE user_id = :userId;
        """;

    private final FriendMapper friendMapper;
    private NamedParameterJdbcTemplate jdbcTemplate;
    public Friend insert(final Friend friend) {
        return jdbcTemplate.queryForObject(INSERT, userToSql(friend), friendMapper);
    }

    public void delete(final Friend friend) {
        try {
            jdbcTemplate.queryForObject(DELETE, userToSql(friend), friendMapper);
        } catch (Exception e) {
            throw new BadRequestException(String.format("Friend id = %d and User id = %d not found ", friend.getFriendId(), friend.getUserId()));
        }
    }

    public List<Friend> findAll(final long userId) {
        try {
            return jdbcTemplate.query(GET_ALL_FRIEND, new MapSqlParameterSource("userId", userId), friendMapper);
        } catch (Exception e) {
            throw new BadRequestException(String.format("User id = %d not found", userId));
        }
    }

    private MapSqlParameterSource userToSql(final Friend friend) {
        final MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("userId", friend.getUserId());
        parameterSource.addValue("friendId", friend.getFriendId());
        return parameterSource;
    }
}
