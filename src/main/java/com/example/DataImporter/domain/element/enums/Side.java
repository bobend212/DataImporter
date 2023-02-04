package com.example.DataImporter.domain.element.enums;

import lombok.*;

@Getter
@AllArgsConstructor
public enum Side {
    SQUARE("SQUARE"),
    ANGLED("ANGLED");

    private final String value;
}