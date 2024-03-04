package ru.asphaltica.LabControl.util.enums;

public enum MineralTitle {
    COARSE1("Крупный №1"),
    COARSE2("Крупный №2"),
    COARSE3("Крупный №3"),
    COARSE4("Крупный №4"),
    COARSE5("Крупный №5"),
    COARSE6("Крупный №6"),

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
