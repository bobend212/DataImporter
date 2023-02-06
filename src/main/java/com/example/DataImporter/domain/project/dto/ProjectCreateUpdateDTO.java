package com.example.DataImporter.domain.project.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectCreateUpdateDTO {

    @NotNull
    private String number;

    @NotNull
    private String name;
}
