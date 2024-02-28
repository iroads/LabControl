package ru.asphaltica.LabControl.util.marshallEnums;

public enum MixLayer {
    O("О"),
    N("Н"),
    T("Т");

    private String layer;

    MixLayer(String layer) {
        this.layer = layer;
    }

    public String getLayer() {
        return layer;
    }
}
