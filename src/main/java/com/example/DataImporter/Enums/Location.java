package com.example.DataImporter.Enums;

import lombok.*;

@Getter
@AllArgsConstructor
public enum Location {
    INTERNAL("INTERNAL"),
    EXTERNAL("EXTERNAL");

    private final String value;
}
