package ru.asphaltica.LabControl.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.asphaltica.LabControl.models.mixComponents.Additive;
import ru.asphaltica.LabControl.models.mixComponents.Bitumen;
import ru.asphaltica.LabControl.models.mixComponents.Mineral;
import ru.asphaltica.LabControl.models.mixComponents.MineralConverter;
import ru.asphaltica.LabControl.util.marshallEnums.MixLayer;
import ru.asphaltica.LabControl.util.marshallEnums.MixTrafic;
import ru.asphaltica.LabControl.util.marshallEnums.MixType;

import java.util.Date;
@Entity
@Table(name="recipe")
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
//    private Mineral stoneFine1;
//    private Mineral stoneFine2;
//    private Mineral stoneCoarse1;
//    private Mineral stoneCoarse2;
//    private Mineral stoneCoarse3;
//    private Mineral stoneCoarse4;
//    private Mineral stoneCoarse5;
//    private Mineral stoneCoarse6;
//
//
//    //Битум
//    private Bitumen bitumen;
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
