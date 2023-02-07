package com.example.DataImporter.domain.element.dto;

import com.example.DataImporter.domain.element.enums.Location;
import com.example.DataImporter.domain.element.enums.Side;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ElementDTO {

    private Long id;
    private Location location;
    private String label;
    private Side side;
    private String material;
    private String grade;
    private int width;
    private int height;
    private int length;
    private int quantity;
    private String reference;
    private Long projectId;

}
