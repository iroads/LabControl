package ru.asphaltica.LabControl.util.enums;

public enum Sito {
    DNO("dno"),
    S0063("0,063"),
    S0125("0,125"),
    S025("0,25"),
    S05("0,5"),
    S1("1"),
    S2("2"),
    S4("4"),
    S56("5,6"),
    S8("8"),
    S112("11,2"),
    S16("16"),
    S224("22,4"),
    S315("31,5");

    private String size;

    Sito(String size) {
        this.size = size;
    }

    public String getSize(){
        return this.size;
    }
}
