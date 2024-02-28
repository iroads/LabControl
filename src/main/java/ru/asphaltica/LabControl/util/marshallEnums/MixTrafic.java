package ru.asphaltica.LabControl.util.marshallEnums;

public enum MixTrafic {
    L("Л"),
    N("Н"),
    T("Т");

    private String trafic;

    MixTrafic(String trafic) {
        this.trafic = trafic;
    }

    public String getTrafic() {
        return trafic;
    }
}
