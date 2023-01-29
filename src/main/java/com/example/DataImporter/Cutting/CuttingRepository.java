package com.example.DataImporter.Cutting;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuttingRepository extends JpaRepository<Cutting, Long> {
}
