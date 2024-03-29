package ru.asphaltica.LabControl.util.enums;

import java.util.Arrays;

public enum MixTraffic {
    L("Л"),
    N("Н"),
    T("Т");

    private String traffic;

    MixTraffic(String traffic) {
        this.traffic = traffic;
    }

    public String getTraffic() {
        return traffic;
    }

    public static MixTraffic findValueByTraffic(String key) {
        return Arrays.stream(MixTraffic.values()).filter(v ->
                v.getTraffic().equals(key)).findFirst().get();
    }
}
