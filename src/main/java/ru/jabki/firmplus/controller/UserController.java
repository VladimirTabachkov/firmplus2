package ru.jabki.firmplus.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.jabki.firmplus.model.User;
import ru.jabki.firmplus.service.UserService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user")
@Tag(name = "Пользователи")
public class UserController {
    private final UserService userService;

    @PostMapping
    @Operation(summary = "Создать пользователя")
    public User create(@RequestBody final User user) {
        return userService.create(user);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить данные пользователя")
    public User getById(@PathVariable("id") String id) {
        return userService.getById(Long.parseLong(id));
    }

    @PatchMapping
    @Operation(summary = "Обновить данные пользователя")
    public void update(@RequestBody final User user) {
        userService.update(user);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить пользователя")
    public void delete(@PathVariable("id") String id) {
        userService.delete(Long.parseLong(id));
    }
}
