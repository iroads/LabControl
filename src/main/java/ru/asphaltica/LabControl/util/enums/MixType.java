package ru.asphaltica.LabControl.util.enums;

import java.util.Arrays;

public enum MixType {

    A5("А5"),
    A8("А8"),
    A11("А11"),
    A16("А16"),
    A22("А22"),
    A32("А32");

    private String type;

    MixType(String type) {
        this.type = type;
    }

    public String getType(){
        return this.type;
    }

    public static MixType findValueByType(String key) {
        return Arrays.stream(MixType.values()).filter(v ->
                v.getType().equals(key)).findFirst().get();
    }
}
