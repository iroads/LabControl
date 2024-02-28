package ru.asphaltica.LabControl;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.asphaltica.LabControl.models.mixComponents.Mineral;
import ru.asphaltica.LabControl.models.mixComponents.MineralConverter;
import ru.asphaltica.LabControl.repositories.RecipeRepository;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class JsonTest {
    public static void main(String[] args) throws IOException {

        Mineral mineral = new Mineral();
        mineral.setManufacturer("ООО Асбест");

        MineralConverter converter = new MineralConverter();
        MineralConverter converter1 = new MineralConverter();

        //преобразовываем все записанное во StringWriter в строку
        String result = converter.convertToDatabaseColumn(mineral);
        System.out.println(result);



        //Mineral mineral2 = mapper.readValue(reader, Mineral.class);

        System.out.println(converter1.convertToEntityAttribute(result).getManufacturer());




    }
}
