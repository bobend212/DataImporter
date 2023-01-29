package com.example.DataImporter.Cutting;

import lombok.*;

import jakarta.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cutting_details")
public class Cutting {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "int_ext")
    private String intExt;
    @Column(name = "sq_ang")
    private String sqAng;
    @Column(name = "ref")
    private String ref;
    @Column(name = "material")
    private String material;
    @Column(name = "grade")
    private String grade;
    @Column(name = "width")
    private int width;
    @Column(name = "height")
    private int height;
    @Column(name = "length")
    private int length;
    @Column(name = "left_cut")
    private float leftCut;
    @Column(name = "right_cut")
    private float rightCut;
    @Column(name = "qty")
    private int qty;
    @Column(name = "set_ref")
    private String setRef;

}
