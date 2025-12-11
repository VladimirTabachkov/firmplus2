package ru.jabki.firmplus.servicetest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.jabki.firmplus.model.User;
import ru.jabki.firmplus.repository.UserRepository;
import ru.jabki.firmplus.service.UserService;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testCreateUser_valid() {
        final User user = getUser();
        when(userRepository.insert(user)).thenReturn(user);
        User result = userService.create(user);
        assertThat(result).isEqualTo(user);
        verify(userRepository).insert(user);
    }

    @Test
    void testUpdateUser_valid() {
        final User user = getUser();
        User updatedFromDb = User.builder()
                .id(user.getId())
                .name("Петров П.П.")
                .login("petrov1971")
                .email("petrov1971@mail.ru")
                .birthday(LocalDate.of(1971, 3, 2))
                .build();

        when(userRepository.update(user)).thenReturn(updatedFromDb);
        User result = userService.update(user);
        assertThat(result.getName()).isEqualTo("Петров П.П.");
        assertThat(result.getLogin()).isEqualTo("petrov1971");
        assertThat(result.getEmail()).isEqualTo("petrov1971@mail.ru");
        assertThat(result.getBirthday()).isEqualTo(LocalDate.of(1971, 3, 2));
        verify(userRepository).update(user);
    }

    @Test
    void testGetUser_valid() {
        final User user = getUser();
        when(userRepository.findById(user.getId())).thenReturn(user);
        User result = userService.getById(user.getId());
        assertThat(result).isEqualTo(user);
        verify(userRepository).findById(user.getId());
    }

    @Test
    void testDeleteUser_valid() {
        long id = 1L;
        doNothing().when(userRepository).delete(id);
        userService.delete(id);
        verify(userRepository).delete(id);
    }

    private User getUser() {
        return User
                .builder()
                .id(1L)
                .name("Иванов И.И.")
                .login("ivanov1980")
                .email("ivanov1980@mail.ru")
                .birthday(LocalDate.of(1980, 11, 3))
                .build();
    }

}
