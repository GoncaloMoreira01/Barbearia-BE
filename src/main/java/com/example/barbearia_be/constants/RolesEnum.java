package com.example.barbearia_be.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RolesEnum {
    CLIENT(0, "CLIENT"),
    BARBER(1, "BARBER");

    private final int id;
    private final String name;
}
