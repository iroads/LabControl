package ru.asphaltica.LabControl.models.mixComponents;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

@Converter
public class MineralConverter implements AttributeConverter<Mineral, String> {

    @Override
    public String convertToDatabaseColumn(Mineral mineral) {
        ObjectMapper mapper = new ObjectMapper();
        StringWriter writer = new StringWriter();
        try {
            mapper.writeValue(writer, mineral);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return writer.toString();
    }

    @Override
    public Mineral convertToEntityAttribute(String s) {
        ObjectMapper mapper = new ObjectMapper();
        StringReader reader = new StringReader(s);
        try {
            return mapper.readValue(reader, Mineral.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
