package ru.jabki.firmplus.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.jabki.firmplus.model.Friend;
import ru.jabki.firmplus.service.FriendsService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/friends")
@Tag(name = "Друзья")
public class FriendController {
    private final FriendsService friendService;

    @PostMapping
    @Operation(summary = "Создать друга")
    public void create(@RequestBody final Friend friend) {
        friendService.create(friend);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить друзей")
    public List<Friend> getById(@PathVariable("userId") Long userId) {
        return friendService.getById(userId);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить друга")
    public void delete(@RequestBody final Friend friend) {
        friendService.delete(friend);
    }
}