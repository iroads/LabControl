package ru.asphaltica.LabControl.util.enums;

import java.util.Arrays;

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

    public static MixLayer findValueByLayer(String key) {
        return Arrays.stream(MixLayer.values()).filter(v ->
                v.getLayer().equals(key)).findFirst().get();
    }
}
