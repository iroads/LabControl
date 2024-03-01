package ru.asphaltica.LabControl.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Rounder {
    //Метод для огругления чисел до второго знака после запятой
    public static double roundDouble(int countAfterPoint, double number) {
        BigDecimal bigDecimal = new BigDecimal(number);
        return bigDecimal.setScale(countAfterPoint, RoundingMode.HALF_UP).doubleValue();
    }
}
