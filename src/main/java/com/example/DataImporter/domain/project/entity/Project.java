package com.example.DataImporter.domain.project.entity;

import lombok.*;

import java.util.List;

import com.example.DataImporter.domain.element.entity.Element;

import jakarta.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String number;

    private String name;

    @OneToMany(mappedBy = "project")
    private List<Element> elements;

}
