package com.example.DataImporter.Element;

import lombok.*;

import jakarta.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@Table(name = "elements")
public class Element {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @Column(name = "qty")
    private int qty;

    @Column(name = "set_ref")
    private String setRef;

    @Column(name = "job_no")
    private int jobNo;
}
