package ru.asphaltica.LabControl.util.enums;

public enum MixLayer {
    O("О"),
    N("Н"),
    V("В");

    private String layer;

    MixLayer(String layer) {
        this.layer = layer;
    }

    public String getLayer() {
        return layer;
    }
}
