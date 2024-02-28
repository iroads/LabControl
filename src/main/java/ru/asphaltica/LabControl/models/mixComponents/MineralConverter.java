package ru.asphaltica.LabControl.models.mixComponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

@Converter
public class MineralConverter implements AttributeConverter<Mineral, String> {
    StringWriter writer;
    ObjectMapper mapper;
    {
        writer = new StringWriter();
        mapper = new ObjectMapper();
    }

    @Override
    public String convertToDatabaseColumn(Mineral mineral) {
        try {
            mapper.writeValue(writer, mineral);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return writer.toString();
    }

    @Override
    public Mineral convertToEntityAttribute(String s) {
        StringReader reader = new StringReader(s);
        try {
            return mapper.readValue(reader, Mineral.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
