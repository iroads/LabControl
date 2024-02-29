package ru.asphaltica.LabControl.util.enums;

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
}
