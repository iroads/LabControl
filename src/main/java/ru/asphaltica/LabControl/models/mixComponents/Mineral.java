package ru.asphaltica.LabControl.models.mixComponents;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.asphaltica.LabControl.util.Rounder;
import ru.asphaltica.LabControl.util.enums.MineralTitle;

@JsonAutoDetect
@Data
@NoArgsConstructor
public class Mineral {
    //Минерал
    private MineralTitle MineralTitle;
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
    //Процентное содержание в смеси без битума
    private double percentage;

    public double getPercentageInMix(double bitumenPercentageUp100){
        return Rounder.roundDouble(2,percentage*(100 + bitumenPercentageUp100)/100);
    }
}
