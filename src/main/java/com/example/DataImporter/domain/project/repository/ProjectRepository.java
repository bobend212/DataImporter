package com.example.DataImporter.domain.project.repository;

import com.example.DataImporter.domain.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findByNumber(String number);
}
