package ru.jabki.firmplus.model;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class User {
    private Long id;
    private String login;
    private String name;
    private String email;
    private LocalDate birthday;
    private static AtomicInteger counter = new AtomicInteger(1);

    public User(final String name, final String email, final String login, final LocalDate birthday) {
        this.id = (long) counter.getAndIncrement();
        this.name = name;
        this.email = email;
        this.login = login;
        this.birthday = birthday;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return this.login;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}