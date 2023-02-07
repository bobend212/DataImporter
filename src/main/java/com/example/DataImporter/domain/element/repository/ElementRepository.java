package com.example.DataImporter.domain.element.repository;

import com.example.DataImporter.domain.element.entity.Element;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ElementRepository extends JpaRepository<Element, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM elements where project_id = :projectId")
    List<Element> findElementsByProjectId(Long projectId);
}