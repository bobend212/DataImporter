package com.example.DataImporter.domain.element.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ExportResponse {
    private String message;
    private String path;
}
