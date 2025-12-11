package ru.jabki.firmplus.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.jabki.firmplus.model.Friend;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class FriendMapper implements RowMapper<Friend> {

    @Override
    public Friend mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Friend
                .builder()
                .userId(rs.getLong("user_id"))
                .friendId(rs.getLong("friend_id"))
                .build();
    }
}