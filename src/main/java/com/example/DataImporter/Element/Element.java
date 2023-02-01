package com.example.DataImporter.Element;

import lombok.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.example.DataImporter.Enums.Location;
import com.example.DataImporter.Enums.Side;
import com.example.DataImporter.Project.Project;

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

    @Enumerated(EnumType.STRING)
    private Location location;

    private String label;

    @Enumerated(EnumType.STRING)
    private Side side;

    private String material;

    private String grade;

    private int width;

    private int height;

    private int length;

    private int quantity;

    private String reference;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
}
