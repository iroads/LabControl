package ru.asphaltica.LabControl.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.asphaltica.LabControl.util.Rounder;
import ru.asphaltica.LabControl.util.enums.Sito;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hot_mix_test_result")
public class HotMixTestResult {

    @Transient
    private final static double GRAVITY_BITUMEN = 1.0;

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
    @Column(name = "gravity_mix_bulk")
    private double gravityMixBulk;
    //Максимальная плотность смеси
    @Column(name = "gravity_mix_maximum")
    private double gravityMixMaximum;

    //Масса пустой корзины для выжигания
    @Column(name = "weight_of_basket")
    private double weightOfBasket;
    //Масса корзины со смесью
    @Column(name = "weight_of_basket_and_mix")
    private double weightOfBasketAndMix;
    //Масса корзины со смесью после выжигания
    @Column(name = "weight_of_basket_and_mix_after_burn")
    private double weightOfBasketAndMixAfterBurn;

    //Поправка к содержанию битума определенному при выжигании на выгорание каменного материала
    //назначается пользователем на основе опыта
    @Column(name = "correction_bitumen_stone_burn")
    private double correctionBitumenStoneBurn;
    //Количество битума назначаемое пользователем, как наиболее вероятное исходя из анализа имеющихся данных
    @Column(name = "bitumen_percentage_in_100_user")
    private double bitumenPercentageIn100User;


    //Партия
    @ManyToOne
    @JoinColumn(name = "batch_id", referencedColumnName = "id")
    private Batch batchSource;

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

        Map<Sito, Double> chopMap = new LinkedHashMap<>();
        if (summaCHOG == 0.0) {
            for (Sito sito : Sito.values()) {
                chopMap.put(sito, 0.0);
            }
        } else {
            //Рассчитываем частные остатки в % и кладем их в отдельный LinkedHashMap
            double summaCHOP = 0;
            for (Map.Entry<Sito, Double> entry : this.getCHOGS().entrySet()) {
                double chop = entry.getValue() * 100 / summaCHOG;
                chop = Rounder.roundDouble(2, chop);
                chopMap.put(entry.getKey(), chop);
                summaCHOP = summaCHOP + chop;
            }
        }
        return chopMap;
    }

    //Метод возвращает полные проходы
    public Map<Sito, Double> getPP() {
        double summaPP = 0;
        Map<Sito, Double> chopMap = this.getCHOPS();
        List<Sito> keys = new ArrayList<>(chopMap.keySet());
        Map<Sito, Double> ppMap = new LinkedHashMap<>();
        for (int i = keys.size() - 1; i >= 0; i--) {
            ppMap.put(keys.get(i), Rounder.roundDouble(2, summaPP));
            summaPP = summaPP + chopMap.get(keys.get(i));
        }
        return ppMap;
    }

    public double getVoids() {
        return this.gravityMixMaximum == 0 ? 0 : Rounder.roundDouble(1, (1 - (this.gravityMixBulk / this.gravityMixMaximum)) * 100);
    }

    //Расчет содержания вяжущего в смеси по результатам выжигания
    public double getBitumenPercentageIn100Burn() {
        double weightOfMix = weightOfBasketAndMix - weightOfBasket;
        double weigthOfMixAfterBurn = weightOfBasketAndMixAfterBurn - weightOfBasket;
        return Rounder.roundDouble(2, (weightOfMix - weigthOfMixAfterBurn) / weightOfMix * 100);
    }

    //Расчет содержания вяжущего по данным рецепта и максимальной плотности пробы
    public double getBitumenPercentageIn100Gmm() {
        double gravityStoneEffective = batchSource.getRecipeSource().getGravityStoneEffective();
        double bitumenPercentageIn100Gmm = Rounder.roundDouble(2, ((gravityStoneEffective * GRAVITY_BITUMEN * 100 / gravityMixMaximum) - (GRAVITY_BITUMEN * 100)) / (gravityStoneEffective - GRAVITY_BITUMEN));
        return bitumenPercentageIn100Gmm;
    }

    //Расчет содержания вяжущего в смеси по результатам выжигания с корректировкой на выгорание материала
    public double getBitumenPercentageIn100BurnCorr() {
        return Rounder.roundDouble(2, getBitumenPercentageIn100Burn() - correctionBitumenStoneBurn);
    }

    //Расчет пористости минерального заполнителя
    public double getPMZ(double pStone) {
        double gravityStoneBulk = batchSource.getRecipeSource().getGravityStoneBulk();
        return gravityStoneBulk == 0 ? 0.0 : Rounder.roundDouble(1, 100 * (1 - ((gravityMixBulk * pStone) / gravityStoneBulk)));
    }

    //Расчет пустот наполненных битумом
    public double getPNB(double PMZ) {
        return Rounder.roundDouble(1, 100 * (PMZ - getVoids()) / PMZ);
    }

    //Расчет пористости минерального заполнителя на основе содержания битума определенного при выжигании
    public double getPMZ_BitumenBurn() {
        return getPMZ(getPStone(getBitumenPercentageIn100Burn()));
    }

    //ПНВ при вышеуказанном ПМЗ
    public double getPNB_BitumenBurn() {
        return getPNB(getPMZ_BitumenBurn());
    }

    //Расчет пористости минерального заполнителя на основе содержания битума определенного по данным рецепта и максимальной плотности образца
    public double getPMZ_BitumenGmm() {
        return getPMZ(getPStone(getBitumenPercentageIn100Gmm()));
    }

    //ПНВ при вышеуказанном ПМЗ
    public double getPNB_BitumenGmm() {
        return getPNB(getPMZ_BitumenGmm());
    }

    //Расчет пористости минерального заполнителя на основе содержания битума определенного при выжигании с корректировкой на выгорание материала
    public double getPMZ_BitumenBurnCorr() {
        return getPMZ(getPStone(getBitumenPercentageIn100BurnCorr()));
    }

    //ПНВ при вышеуказанном ПМЗ
    public double getPNB_BitumenBurnCorr() {
        return getPNB(getPMZ_BitumenBurnCorr());
    }

    //Расчет пористости минерального заполнителя на основе содержания битума определенного при выжигании с корректировкой на выгорание материала
    public double getPMZ_BitumenUser() {
        return getPMZ(getPStone(bitumenPercentageIn100User));
    }

    //ПНВ при вышеуказанном ПМЗ
    public double getPNB_BitumenUser() {
        return getPNB(getPMZ_BitumenUser());
    }


    //Расчет содержания минерального заполнителя в долях 1
    public double getPStone(double bitumenPercentageIn100) {
        return Rounder.roundDouble(4, (100 - bitumenPercentageIn100) / 100);
    }
}
