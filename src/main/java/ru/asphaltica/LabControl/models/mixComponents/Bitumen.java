package ru.asphaltica.LabControl.models.mixComponents;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonAutoDetect
@Data
@NoArgsConstructor
public class Bitumen {
    //Производитель
    private String manufacturer;
    //Марка вяжущего
    private String grade;
    //Процентное содержание сверх ста процентов минеральных материалов
    private double percentageUp100;
}
