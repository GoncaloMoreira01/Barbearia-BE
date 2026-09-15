package com.example.barbearia_be.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEnum {
    INACTIVE(0, "INACTIVE"),
    ACTIVE(1, "ACTIVE");

    private final int id;
    private final String name;
}
