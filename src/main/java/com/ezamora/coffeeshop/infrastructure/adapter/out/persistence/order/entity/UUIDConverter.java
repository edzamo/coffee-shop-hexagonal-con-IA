package com.ezamora.coffeeshop.infrastructure.adapter.out.persistence.order.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.UUID;

/**
 * Converter class to convert between UUID and String for database persistence.
 * 
 * Le dice a Hibernate:
 * • “Cuando guardes este campo UUID, conviértelo a String.”
 * • “Cuando leas de la base de datos un String, conviértelo a UUID.”
 * 
 * Esto es necesario porque MySQL no tiene un tipo UUID nativo (a diferencia de
 * PostgreSQL, por ejemplo), y lo guardamos como VARCHAR(36).
 */
@Converter(autoApply = true)
public class UUIDConverter implements AttributeConverter<UUID, String> {

    @Override
    public String convertToDatabaseColumn(UUID uuid) {
        return uuid != null ? uuid.toString() : null;
    }

    @Override
    public UUID convertToEntityAttribute(String dbData) {
        return dbData != null ? UUID.fromString(dbData) : null;
    }
}