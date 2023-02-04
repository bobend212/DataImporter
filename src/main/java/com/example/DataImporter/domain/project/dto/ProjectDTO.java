package com.example.DataImporter.domain.project.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {

    private Long id;
    private String number;
    private String name;

    @With
    private int totalRows;
}
