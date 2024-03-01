package ru.asphaltica.LabControl.models.mixComponents;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonAutoDetect
@Data
@NoArgsConstructor
public class Additive {
    //Производитель
    private String manufacturer;
    //Название добавки
    private String name;
    //Процентное содержание сверх ста процентов минеральных материалов
    private double percentageUp100;
}
