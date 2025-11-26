package ru.jabki.firmplus.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.jabki.firmplus.model.Friend;
import ru.jabki.firmplus.service.FriendsService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/friends")
@Tag(name = "Друзья")
public class FriendController {

    public final FriendsService friendService;

    public FriendController(final FriendsService friendService) {
        this.friendService = friendService;
    }

    @PostMapping
    @Operation(summary = "Создать друга")
    public void create(@RequestBody final Friend friend) {
        friendService.addFriend(friend.getUserId(), friend.getFriendId());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить друзей")
    public List<Friend> getById(@PathVariable("id") String userId) {
        return friendService.getFriend(Long.valueOf(userId));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить друга")
    public void delete(@RequestParam(required = true) Long userId, @RequestParam(required = true) Long FriendId) {
        friendService.delete(userId, FriendId);
    }
}