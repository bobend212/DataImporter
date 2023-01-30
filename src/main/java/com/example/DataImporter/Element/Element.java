package com.example.DataImporter.Element;

import lombok.*;

import jakarta.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "elements")
public class Element {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String intExt;

    private String sqAng;

    private String ref;

    private String material;

    private String grade;

    private int width;

    private int height;

    private int length;

    private int qty;

    private String setRef;

    private int jobNo;
}
