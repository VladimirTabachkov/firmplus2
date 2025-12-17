package ru.jabki.firmplus.model;

public class Genre
{
    public String[] stringList = new String[]
                {"ACTION",
                 "ADVENTURE",
                 "ANIMATED",
                 "COMEDY",
                 "DRAMA",
                 "FANTASY",
                 "HISTORICAL",
                 "HORROR",
                 "NOIR",
                 "MUSICAL",
                 "WESTERN"};

    public String getGenre(int id) {
        return stringList[id];
    }
};