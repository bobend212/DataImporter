package com.example.DataImporter.DTO;

import com.example.DataImporter.Element.Element;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ElementExportDTO {
    private Long recordsFound;
    private LocalDateTime date;
    private List<Element> elements;
}
