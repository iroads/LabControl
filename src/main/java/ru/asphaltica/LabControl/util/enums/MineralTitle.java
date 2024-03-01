package ru.asphaltica.LabControl.util.enums;

public enum MineralTitle {
    COARSE1("Крупный №1"),
    COARSE2("Крупный №1"),
    COARSE3("Крупный №1"),
    COARSE4("Крупный №1"),
    COARSE5("Крупный №1"),
    COARSE6("Крупный №1"),

    FINE1("Мелкий №1"),
    FINE2("Мелкий №2"),

    POWDER("Минеральный порошок");

    private String title;

    MineralTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
