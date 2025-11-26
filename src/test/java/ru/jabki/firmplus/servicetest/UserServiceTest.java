package ru.jabki.firmplus.servicetest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.jabki.firmplus.model.User;
import ru.jabki.firmplus.service.UserService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserServiceTest {
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
        userService.addUser(
                new User("Петров П.П.",
                        "petrov1971@mail.ru",
                        "petrov1971",
                        LocalDate.of(1971, 3, 2)
                )
        );
        userService.addUser(
                new User("Сидоров С.С.",
                        "sidorov1974@mail.ru",
                        "sidorov1974",
                        LocalDate.of(1974, 12, 8)
                ));
    }

    @Test
    void testCreate() {
        User user = userService.addUser(
                new User("Иванов И.И.",
                        "ivanov1980@mail.ru",
                        "ivanov1980",
                        LocalDate.of(1980, 11, 3)
                )
        );
        assertEquals("Иванов И.И.", user.getName());
        assertEquals("ivanov1980@mail.ru", user.getEmail());
        assertEquals(LocalDate.of(1980, 11, 3), user.getBirthday());
    }

}
