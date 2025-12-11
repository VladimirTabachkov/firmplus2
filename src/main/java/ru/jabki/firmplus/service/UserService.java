package ru.jabki.firmplus.service;

import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import ru.jabki.firmplus.exception.UserException;
import ru.jabki.firmplus.model.User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.jabki.firmplus.repository.UserRepository;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional(rollbackFor = Exception.class)
    public User create(final User user) {
        validate(user);
        return userRepository.insert(user);
    }

    private void validate(User user) {
        if (user == null) {
            throw new UserException("User is null");
        }
        if (!StringUtils.hasText(user.getEmail())) {
            throw new UserException("User email is empty");
        }
        if (!StringUtils.hasText(user.getName())) {
            throw new UserException("User name is empty");
        }
        if (!StringUtils.hasText(user.getLogin())) {
            throw new UserException("User login is empty");
        }
        if (user.getBirthday().isAfter(LocalDate.now())) {
            throw new UserException("User birthday not correct");
        }
    }

    @Transactional(readOnly = true)
    public User getById(final Long id) {
        final User user = userRepository.findById(id);
        if (user == null) {
            throw new UserException("User not found");
        }
        return user;
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(final Long id) {
        userRepository.delete(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public User update(User user) {
        validate(user);
        final User existUser = getById(user.getId());
        existUser.setName(user.getName());
        existUser.setEmail(user.getEmail());
        existUser.setBirthday(user.getBirthday());
        return userRepository.update(existUser);
    }
}
