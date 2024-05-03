package com.example.appreactspring.config.jackson;

import com.example.appreactspring.core.JsonConverter;
import com.example.appreactspring.exception.JsonConverterException;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JsonConverterJacksonImpl implements JsonConverter {

    private final ObjectMapper objectMapper;

    public JsonConverterJacksonImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public String toJson(Object object) {
        try {
            return this.objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new JsonConverterException("Error writing to JSON", e);
        }
    }

}
