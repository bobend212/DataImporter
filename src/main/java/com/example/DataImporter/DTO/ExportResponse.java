package com.example.DataImporter.DTO;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ExportResponse {
    private Long noOfRecords;
    private Timestamp date;
    private String message;
}
