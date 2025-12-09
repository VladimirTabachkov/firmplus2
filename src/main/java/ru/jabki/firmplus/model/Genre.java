package ru.jabki.firmplus.model;

public enum Genre {
    ACTION(1),
    ADVENTURE(2),
    ANIMATED(3),
    COMEDY(4),
    DRAMA(5),
    FANTASY(6),
    HISTORICAL(7),
    HORROR(8),
    NOIR(9),
    MUSICAL(10),
    WESTERN(11);

    private final int id;

    public int gatId() {
        return this.id;
    }

    Genre(int id) {
        this.id = id;
    }
};