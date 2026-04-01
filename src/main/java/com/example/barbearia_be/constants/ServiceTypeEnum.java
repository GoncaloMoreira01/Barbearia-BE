package com.example.barbearia_be.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ServiceTypeEnum {
    HAIR(1, "HAIR", 12.0),
    BEARD(2, "BEARD", 6.0),
    HAIR_PLUS_BEARD(3, "HAIR_PLUS_BEARD", 18.0);

    private final int id;
    private final String name;
    private final Double price;
}
