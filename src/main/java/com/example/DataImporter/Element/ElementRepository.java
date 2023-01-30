package com.example.DataImporter.Element;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ElementRepository extends JpaRepository<Element, Long> {
}
