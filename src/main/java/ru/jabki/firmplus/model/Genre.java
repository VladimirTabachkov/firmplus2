package ru.jabki.firmplus.model;

public enum Genre {
    ACTION(1),
    ADVENTURE(2),
    ANIMATED(3),
    COMEDY(4),
    DRAMA(4),
    FANTASY(5),
    HISTORICAL(6),
    HORROR(7),
    NOIR(8),
    MUSICAL(9),
    WESTERN(10);

    private final int id;

    public int gatId() {
        return this.id;
    }

    Genre(int id) {
        this.id = id;
    }
};