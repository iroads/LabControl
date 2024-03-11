package ru.asphaltica.LabControl.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.asphaltica.LabControl.models.mixComponents.*;
import ru.asphaltica.LabControl.util.Rounder;
import ru.asphaltica.LabControl.util.Sito;
import ru.asphaltica.LabControl.util.enums.MineralTitle;
import ru.asphaltica.LabControl.util.enums.MixLayer;
import ru.asphaltica.LabControl.util.enums.MixTraffic;
import ru.asphaltica.LabControl.util.enums.MixType;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "recipe")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //@DateTimeFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private LocalDateTime createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    private LocalDateTime modifyDate;

    //Классификация смеси

    //Тип смеси А - асфальтобетонная смесь + Номинально максимальный размер минерального заполнителя (5,8,11,16,22,32)
    @Enumerated(EnumType.STRING)
    @Column(name = "mix_type")
    private MixType mixType;
    //Конструктивный слой
    @Enumerated(EnumType.STRING)
    @Column(name = "mix_layer")
    private MixLayer mixLayer;
    //Условия дорожного движения
    @Enumerated(EnumType.STRING)
    @Column(name = "mix_traffic")
    private MixTraffic mixTraffic;

    //Минеральные материалы
    @Convert(converter = MineralConverter.class)
    @Column(name = "powder")
    private Mineral powder;
    @Convert(converter = MineralConverter.class)
    @Column(name = "stone_fine1")
    private Mineral stoneFine1;
    @Convert(converter = MineralConverter.class)
    @Column(name = "stone_fine2")
    private Mineral stoneFine2;
    @Convert(converter = MineralConverter.class)
    @Column(name = "stone_coarse1")
    private Mineral stoneCoarse1;
    @Convert(converter = MineralConverter.class)
    @Column(name = "stone_coarse2")
    private Mineral stoneCoarse2;
    @Convert(converter = MineralConverter.class)
    @Column(name = "stone_coarse3")
    private Mineral stoneCoarse3;
    @Convert(converter = MineralConverter.class)
    @Column(name = "stone_coarse4")
    private Mineral stoneCoarse4;
    @Convert(converter = MineralConverter.class)
    @Column(name = "stone_coarse5")
    private Mineral stoneCoarse5;
    @Convert(converter = MineralConverter.class)
    @Column(name = "stone_coarse6")
    private Mineral stoneCoarse6;
    //Битум
    @Convert(converter = BitumenConverter.class)
    @Column(name = "bitumen")
    private Bitumen bitumen;

    //Зерновой состав
    @Column(name = "PP0063")
    private double PP0063;
    @Column(name = "PP0125")
    private double PP0125;
    @Column(name = "PP025")
    private double PP025;
    @Column(name = "PP05")
    private double PP05;
    @Column(name = "PP1")
    private double PP1;
    @Column(name = "PP2")
    private double PP2;
    @Column(name = "PP4")
    private double PP4;
    @Column(name = "PP56")
    private double PP56;
    @Column(name = "PP8")
    private double PP8;
    @Column(name = "PP112")
    private double PP112;
    @Column(name = "PP16")
    private double PP16;
    @Column(name = "PP224")
    private double PP224;
    @Column(name = "PP315")
    private double PP315;

    //Добавки
    @Convert(converter = AdditiveConverter.class)
    @Column(name = "additive1")
    private Additive additive1;
    @Convert(converter = AdditiveConverter.class)
    @Column(name = "additive2")
    private Additive additive2;

    //Физико-механические показатели
    //Объемная плотность асфальтобетонной смеси
    @Column(name = "gravity_mix_bulk")
    private double gravityMixBulk;
    //Максимальная плотность асфальтобетонной смеси
    @Column(name = "gravity_mix_maximum")
    private double gravityMixMaximum;
    //Водостойкость
    @Column(name = "water_resistance")
    private double waterResistance;
    //Глубина колеи
    @Column(name = "track_depth")
    private double trackDepth;
    //Филиал
    @ManyToOne
    @JoinColumn(name = "unit_id", referencedColumnName = "id")
    private Unit unit;
    @OneToMany(mappedBy = "recipeSource")
    private List<Batch> batches;
    @Transient
    private int unitId;

    public Map<Sito, Double> getPP() {
        Map<Sito, Double> PPMap = new LinkedHashMap<>();
        PPMap.put(Sito.S315, PP315);
        PPMap.put(Sito.S224, PP224);
        PPMap.put(Sito.S16, PP16);
        PPMap.put(Sito.S112, PP112);
        PPMap.put(Sito.S8, PP8);
        PPMap.put(Sito.S56, PP56);
        PPMap.put(Sito.S4, PP4);
        PPMap.put(Sito.S2, PP2);
        PPMap.put(Sito.S1, PP1);
        PPMap.put(Sito.S05, PP05);
        PPMap.put(Sito.S025, PP025);
        PPMap.put(Sito.S0125, PP0125);
        PPMap.put(Sito.S0063, PP0063);
        return PPMap;
    }

    public List<Mineral> getMinerals() {
        List<Mineral> minerals = new ArrayList<>();
        stoneCoarse1.setMineralTitle(MineralTitle.COARSE1);
        stoneCoarse2.setMineralTitle(MineralTitle.COARSE2);
        stoneCoarse3.setMineralTitle(MineralTitle.COARSE3);
        stoneCoarse4.setMineralTitle(MineralTitle.COARSE4);
        stoneCoarse5.setMineralTitle(MineralTitle.COARSE5);
        stoneCoarse6.setMineralTitle(MineralTitle.COARSE6);
        stoneFine1.setMineralTitle(MineralTitle.FINE1);
        stoneFine2.setMineralTitle(MineralTitle.FINE2);
        powder.setMineralTitle(MineralTitle.POWDER);
        minerals.add(stoneCoarse1);
        minerals.add(stoneCoarse2);
        minerals.add(stoneCoarse3);
        minerals.add(stoneCoarse4);
        minerals.add(stoneCoarse5);
        minerals.add(stoneCoarse6);
        minerals.add(stoneFine1);
        minerals.add(stoneFine2);
        minerals.add(powder);
        return minerals;
    }
    //Расчет содержания воздушных пустот
    public double getVoids() {
        return Rounder.roundDouble(1,(1-(gravityMixBulk/gravityMixMaximum))*100);
    }
    //Расчет пористости минерального заполнителя
    public double getPMZ() {
        //Числитель формулы
        double summa = getMinerals().stream().mapToDouble(mineral -> mineral.getPercentage()).sum();
        //Знаменатель формулы
        double percentageDiviedByGravity = getMinerals().stream().filter(mineral -> mineral.getPercentage() !=0)
                .mapToDouble(mineral -> mineral.getPercentage()/(mineral.getGravityStoneAverage() == 0 ?
                        mineral.getGravityStoneApparent()
                        : mineral.getGravityStoneAverage())).sum();
        //Общая объемная плотность каменных материалов
        double gravityStoneBulk = Rounder.roundDouble(3,summa/percentageDiviedByGravity);
        //Количество минерального заполнителя в асфальтобетонной смеси с учетом содержания битумного вяжущего, включенного в 100% состава смеси, доли единиц
        double pStone = Rounder.roundDouble(3,(100 - getBitumenPercentageIn100())/100);
        return Rounder.roundDouble(1, (1 - gravityMixBulk*pStone/gravityStoneBulk)*100);
    }

    //Расчет содержания битума в 100% асфальтобетонной смеси
    public double getBitumenPercentageIn100() {
        return Rounder.roundDouble(2, bitumen.getPercentageUp100()*100/(100+bitumen.getPercentageUp100()));
    }

    //Расчет пустот наполненных вяжущим ПНБ
    public double getPNB(){
        double pmz = getPMZ();
        double voids = getVoids();
        return Rounder.roundDouble(1, ((pmz - voids) / pmz)*100);
    }

    //Получение полного названия смеси
    public String getMixTitle(){
        String mixTitle = new String(this.mixType.getType() + this.mixLayer.getLayer() + this.mixTraffic.getTraffic());
        return mixTitle;
    }


}
