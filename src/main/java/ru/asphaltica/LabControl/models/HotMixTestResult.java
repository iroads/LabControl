package ru.asphaltica.LabControl.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.asphaltica.LabControl.util.Sito;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hot_mix_test_result")
public class HotMixTestResult {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //Зерновой состав смеси, представленный в виде частных остатков в граммах
    @Column(name = "dno")
    private double dno;
    @Column(name = "chog0063")
    private double chog0063;
    @Column(name = "chog0125")
    private double chog0125;
    @Column(name = "chog025")
    private double chog025;
    @Column(name = "chog05")
    private double chog05;
    @Column(name = "chog1")
    private double chog1;
    @Column(name = "chog2")
    private double chog2;
    @Column(name = "chog4")
    private double chog4;
    @Column(name = "chog56")
    private double chog56;
    @Column(name = "chog8")
    private double chog8;
    @Column(name = "chog112")
    private double chog112;
    @Column(name = "chog16")
    private double chog16;
    @Column(name = "chog224")
    private double chog224;
    @Column(name = "chog315")
    private double chog315;

    //Объемная плотность смеси
    @Column(name = "gmb")
    private double gmb;
    //Максимальная плотность смеси
    @Column(name = "gmm")
    private double gmm;

    //Метод кладет в LinkedHashMap значения частных остатков на ситах
    public Map<Sito, Double> getCHOGS() {
        Map<Sito, Double> chogMap = new LinkedHashMap<>();
        chogMap.put(Sito.S315, chog315);
        chogMap.put(Sito.S224, chog224);
        chogMap.put(Sito.S16, chog16);
        chogMap.put(Sito.S112, chog112);
        chogMap.put(Sito.S8, chog8);
        chogMap.put(Sito.S56, chog56);
        chogMap.put(Sito.S4, chog4);
        chogMap.put(Sito.S2, chog2);
        chogMap.put(Sito.S1, chog1);
        chogMap.put(Sito.S05, chog05);
        chogMap.put(Sito.S025, chog025);
        chogMap.put(Sito.S0125, chog0125);
        chogMap.put(Sito.S0063, chog0063);
        chogMap.put(Sito.DNO, dno);
        return chogMap;
    }

    //Метод возвращает частные остатки в %
    public Map<Sito, Double> getCHOPS() {
        //Определяем сумму частных остатков в граммах
        double summaCHOG = 0;
        for (Map.Entry<Sito, Double> entry : this.getCHOGS().entrySet()) {
            summaCHOG = summaCHOG + entry.getValue();
        }
        //Рассчитываем частные остатки в % и кладем их в отдельный LinkedHashMap
        Map<Sito, Double> chopMap = new LinkedHashMap<>();
        double summaCHOP = 0;
        for (Map.Entry<Sito, Double> entry : this.getCHOGS().entrySet()) {
            double chop = entry.getValue() * 100 / summaCHOG;
            chop = roundDoubleTwo(chop);
            chopMap.put(entry.getKey(), chop);
            summaCHOP = summaCHOP + chop;
        }
        //Проверяем равна ли сумма частных остатков в % 100%, если нет то уравниваем до 100% добавлением разницы на DNO
        if (summaCHOP != 100.0) {
            chopMap.put(Sito.DNO, roundDoubleTwo(chopMap.get(Sito.DNO) + 100 - summaCHOP));
        }
        return chopMap;
    }

    //Метод возвращает полные проходы
    public Map<Sito, Double> getPP() {
        double summaPP = 0;
        Map<Sito, Double> chopMap = this.getCHOPS();
        List<Sito> keys = new ArrayList<>(chopMap.keySet());
        Map<Sito, Double> ppMap = new LinkedHashMap<>();
        for (Sito sito : keys) {
            ppMap.put(sito, 0.0);
        }

        for (int i = keys.size() - 1; i >= 0; i--) {
            ppMap.put(keys.get(i), roundDoubleTwo(summaPP));
            summaPP = summaPP + chopMap.get(keys.get(i));
        }
        return ppMap;
    }

    public double getVoids() {
        double voids = (1 - (this.gmb / this.gmm)) * 100;
        return roundDoubleOne(voids);
    }

    //Метод для огругления чисел до второго знака после запятой
    private double roundDoubleTwo(double number) {
        BigDecimal bigDecimal = new BigDecimal(number);
        return bigDecimal.setScale(2, RoundingMode.UP).doubleValue();
    }

    //Метод для огругления чисел до первого знака после запятой
    private double roundDoubleOne(double number) {
        BigDecimal bigDecimal = new BigDecimal(number);
        return bigDecimal.setScale(1, RoundingMode.UP).doubleValue();
    }
}
