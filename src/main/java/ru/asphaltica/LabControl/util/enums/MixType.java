package ru.asphaltica.LabControl.util.enums;

public enum MixType {

    A5("A5"),
    A8("A8"),
    A11("A11"),
    A16("A16"),
    A22("A22"),
    A32("A32");

    private String type;

    MixType(String type) {
        this.type = type;
    }

    public String getType(){
        return this.type;
    }
}
