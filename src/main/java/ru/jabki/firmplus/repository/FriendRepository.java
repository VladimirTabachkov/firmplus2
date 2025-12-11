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

    private static final String IS_EXISTS = """
        SELECT COUNT(*) 
        FROM filmplus.friend
        WHERE user_id = :userId AND friend_id = :friendId
        """;

    private final FriendMapper friendMapper;
    private NamedParameterJdbcTemplate jdbcTemplate;

    public Friend insert(final Friend friend) {
        return jdbcTemplate.queryForObject(INSERT, friendToSql(friend), friendMapper);
    }

    public void delete(final Friend friend) {
        try {
            jdbcTemplate.queryForObject(DELETE, friendToSql(friend), friendMapper);
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

    public boolean exists(long userId, long friendId) {
        return jdbcTemplate.queryForObject(IS_EXISTS, friendIdsToSql(userId, friendId), Integer.class) > 0;
    }

    private MapSqlParameterSource friendToSql(final Friend friend) {
        final MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("userId", friend.getUserId());
        parameterSource.addValue("friendId", friend.getFriendId());
        return parameterSource;
    }

    private MapSqlParameterSource friendIdsToSql(final Long userId, final Long friendId) {
        final MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("userId", userId);
        parameterSource.addValue("friendId", friendId);
        return parameterSource;
    }
}
