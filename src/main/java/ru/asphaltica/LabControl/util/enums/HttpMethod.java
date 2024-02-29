package ru.asphaltica.LabControl.util.enums;

public enum HttpMethod {
    POST("POST","Создать"),
    PATCH("PATCH", "Редактировать");

    private String method;
    private String button;

    HttpMethod(String method, String button) {
        this.method = method;
        this.button = button;
    }

    public String getMethod() {
        return method;
    }

    public String getButton() {
        return button;
    }
}
