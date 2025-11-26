package ru.jabki.firmplus.service;

import ru.jabki.firmplus.exception.FilmException;
import ru.jabki.firmplus.exception.UserException;
import ru.jabki.firmplus.model.User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
public class UserService {
    private static Set<User> users = new HashSet<>();

    public User addUser(User user) {
        validateUser(user);
        user.setId((long) users.size());
        users.add(user);
        return user;
    }

    public User addUser(String name, String email, String login, LocalDate birthday) {
        validateUserData(name, email, login, birthday);
        User newuser = new User(name, email, login, birthday);
        newuser.setId((long) users.size());
        users.add(newuser);
        return newuser;
    }

    private void validateUser(User user) {
        if (user == null) {
            throw new UserException("User is null");
        }
        validateUserData(user.getName(), user.getEmail(), user.getLogin(), user.getBirthday());
    }

    private void validateUserData(String name, String email, String login, LocalDate birthday) {

        if (!StringUtils.hasText(email) || !StringUtils.hasText(name)) {
            throw new UserException("One of the parameters is empty: name - " + name + " email - " + email);
        }
        if (!StringUtils.hasText(login) || birthday.isAfter(LocalDate.now())) {
            throw new UserException("One of the parameters is empty: login - " + login + " birthday - " + birthday);
        }
    }

    public User getbyId(final Long id) {
        return users.stream().filter(f -> Objects.equals(f.getId(), id)).findFirst().orElseThrow(() -> new FilmException("Movie not found"));
    }

    public void deleteUser(final Long id) {
        users.remove(getbyId(id));
    }

    public void updateUser(User user) {
        User tmp = getbyId(user.getId());
        tmp.setName(user.getName());
        tmp.setEmail(user.getEmail());
        tmp.setBirthday(user.getBirthday());
    }
}
