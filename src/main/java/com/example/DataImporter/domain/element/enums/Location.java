package com.example.DataImporter.domain.element.enums;

import lombok.*;

@Getter
@AllArgsConstructor
public enum Location {
    INTERNAL("INTERNAL"),
    EXTERNAL("EXTERNAL");

    private final String value;
}
