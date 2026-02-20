package com.example.barbearia_be.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum Roles {
    CLIENT(0, "CLIENT"),
    BARBER(1, "BARBER");

    private final int id;
    private final String name;
}
