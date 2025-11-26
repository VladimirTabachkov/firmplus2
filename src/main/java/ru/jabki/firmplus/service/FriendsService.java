package ru.jabki.firmplus.service;

import org.springframework.stereotype.Service;
import ru.jabki.firmplus.model.Friend;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class FriendsService {
    private List<Friend> friendList;

    public FriendsService() {
        this.friendList = new ArrayList<>();
    }

    public void addFriend(Long userId, Long friendId) {
        friendList.add(new Friend(userId, friendId));
    }

    public void delete(Long userId, Long friendId) {
        friendList.remove(new Friend(userId, friendId));
    }

    public List<Friend> getFriend(Long friend) {
        return friendList.stream().filter(f -> (!(friend == null) && (Objects.equals(f.getUserId(), friend) || Objects.equals(f.getFriendId(), friend)))).toList();
    }
}