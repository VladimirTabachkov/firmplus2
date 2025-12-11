package ru.jabki.firmplus.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.jabki.firmplus.model.Friend;
import ru.jabki.firmplus.repository.FriendRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class FriendsService {
    private final FriendRepository friendRepository;

    @Transactional(rollbackFor = Exception.class)
    public Friend create(final Friend friend) {
        return friendRepository.insert(friend);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(final Friend friend) {
        friendRepository.delete(friend);
    }

    @Transactional(readOnly = true)
    public List<Friend> getById(final long userId) {
        return friendRepository.findAll(userId);
    }
}