package com.example.DataImporter.DTO;

import com.example.DataImporter.Element.Element;
import lombok.*;

import java.util.List;
import java.sql.Timestamp;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ElementExportDTO {
    private Long recordsFound;
    private Timestamp date;
    private List<Element> elements;
}
