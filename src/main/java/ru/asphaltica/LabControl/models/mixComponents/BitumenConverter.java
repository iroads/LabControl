package ru.asphaltica.LabControl.models.mixComponents;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

@Converter
public class BitumenConverter implements AttributeConverter<Bitumen, String> {
    @Override
    public String convertToDatabaseColumn(Bitumen bitumen) {
        ObjectMapper mapper = new ObjectMapper();
        StringWriter writer = new StringWriter();
        try {
            mapper.writeValue(writer, bitumen);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return writer.toString();
    }

    @Override
    public Bitumen convertToEntityAttribute(String s) {
        ObjectMapper mapper = new ObjectMapper();
        StringReader reader = new StringReader(s);
        try {
            return mapper.readValue(reader, Bitumen.class);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
