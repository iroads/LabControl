package ru.asphaltica.LabControl.models.mixComponents;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

@Converter
public class AdditiveConverter implements AttributeConverter<Additive, String> {
    @Override
    public String convertToDatabaseColumn(Additive additive) {
        ObjectMapper mapper = new ObjectMapper();
        StringWriter writer = new StringWriter();
        try {
            mapper.writeValue(writer, additive);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return writer.toString();
    }

    @Override
    public Additive convertToEntityAttribute(String s) {
        ObjectMapper mapper = new ObjectMapper();
        StringReader reader = new StringReader(s);
        try {
            return mapper.readValue(reader, Additive.class);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
