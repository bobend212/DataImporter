package com.example.DataImporter.DTO;

import com.example.DataImporter.Enums.Location;
import com.example.DataImporter.Enums.Side;

import lombok.*;

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
