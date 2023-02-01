package com.example.DataImporter.Enums;

import lombok.*;

@Getter
@AllArgsConstructor
public enum Side {
    SQUARE("SQUARE"),
    ANGLED("ANGLED");

    private final String value;
}