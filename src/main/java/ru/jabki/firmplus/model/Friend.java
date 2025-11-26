package ru.jabki.firmplus.model;

public class Friend {

    private final Long userId;
    private final Long friendId;

    public Friend(Long userId, Long friendId) {
        this.userId = userId;
        this.friendId = friendId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public Long getFriendId() {
        return this.friendId;
    }
}