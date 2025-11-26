package ru.jabki.firmplus.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import ru.jabki.firmplus.model.User;
import ru.jabki.firmplus.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "Пользователи")
public class UserController {
    private UserService userLogic;

    public UserController(UserService users) {
        this.userLogic = users;
    }

    @PostMapping
    @Operation(summary = "Создать пользователя")
    public User createParam(@RequestParam String name, @RequestParam String email, @RequestParam String login, @RequestParam LocalDate birthday) {
        return userLogic.addUser(name, email, login, birthday);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить данные пользователя")
    public User getById(@PathVariable("id") String id) {
        return userLogic.getbyId(Long.parseLong(id));
    }

    @PatchMapping
    @Operation(summary = "Обновить данные пользователя")
    public void update(@RequestBody final User user) {
        userLogic.updateUser(user);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить пользователя")
    public void delete(@PathVariable("id") String id) {
        userLogic.deleteUser(Long.parseLong(id));
    }
}
