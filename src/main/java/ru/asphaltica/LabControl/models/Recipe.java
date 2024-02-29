package ru.asphaltica.LabControl.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.asphaltica.LabControl.models.mixComponents.*;

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
//    //Дата создания рецепта
//    private Date date;

    //Классификация смеси

//    //Тип смеси А - асфальтобетонная смесь + Номинально максимальный размер минерального заполнителя (5,8,11,16,22,32)
//    private MixType mixType;
//    //Конструктивный слой
//    private MixLayer mixLayer;
//    //Условия дорожного движения
//    private MixTrafic mixTrafic;

    //Минеральные материалы
    @Convert(converter = MineralConverter.class)
    @Column(name="powder")
    private Mineral powder;
    @Convert(converter=MineralConverter.class)
    @Column(name="stone_fine1")
    private Mineral stoneFine1;
    @Convert(converter=MineralConverter.class)
    @Column(name="stone_fine2")
    private Mineral stoneFine2;
    @Convert(converter=MineralConverter.class)
    @Column(name="stone_coarse1")
    private Mineral stoneCoarse1;
    @Convert(converter=MineralConverter.class)
    @Column(name="stone_coarse2")
    private Mineral stoneCoarse2;
    @Convert(converter=MineralConverter.class)
    @Column(name="stone_coarse3")
    private Mineral stoneCoarse3;
    @Convert(converter = MineralConverter.class)
    @Column(name="stone_coarse4")
    private Mineral stoneCoarse4;
    @Convert(converter=MineralConverter.class)
    @Column(name="stone_coarse5")
    private Mineral stoneCoarse5;
    @Convert(converter=MineralConverter.class)
    @Column(name="stone_coarse6")
    private Mineral stoneCoarse6;
    //Битум
    @Convert(converter=BitumenConverter.class)
    @Column(name = "bitumen")
    private Bitumen bitumen;
//
//    //Добавки
//    private Additive additive1;
//    private Additive additive2;
//    private Additive additive3;
//
//    //Физико-механические показатели
//
//    //Объемная плотность асфальтобетонной смеси
//    private double gravityMixBulk;
//    //Максимальная плотность асфальтобетонной смеси
//    private double gravityMixMaximum;
}
