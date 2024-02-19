package ru.asphaltica.LabControl.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Асфальтосмесительная установка
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="plant")
public class Plant {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //Модель асфальтосмесительной установки
    @Column(name="model")
    private String model;

    //Место расположение асфальтосмесительной установки
    @Column(name="location")
    private String location;

    //Производительность т/ч
    @Column(name="productivity")
    private int productivity;

    //Подразделение в котором находится асфальтосмесительная установка
    @ManyToOne
    @JoinColumn(name = "unit_id", referencedColumnName = "id")
    private Unit unit;

}
