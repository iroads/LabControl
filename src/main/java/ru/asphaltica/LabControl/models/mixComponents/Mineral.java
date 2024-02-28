package ru.asphaltica.LabControl.models.mixComponents;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonAutoDetect
@Data
@NoArgsConstructor
public class Mineral {
    //Найменование карьера
    private String manufacturer;
    //Крупность материала (фракция)
    private String grain;

    //Плотности материала для системы Маршалла
    //Средняя плотность
    private double gravityStoneAverage;
    //Истинная плотность
    private double gravityStoneApparent;

    //Плотности для системы Superpave
    //Объемная плотность
    private double gravityStoneBulk;
    //Максимальная плотность
    private double gravityStoneMaximum;
    //Процентное содержание в смеси
    private double percentage;
}
