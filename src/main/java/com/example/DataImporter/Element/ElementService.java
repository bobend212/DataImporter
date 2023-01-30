package com.example.DataImporter.Element;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.DataImporter.parser.FileReader;

import java.io.IOException;
import java.util.List;

@Service
public class ElementService {

    private final ElementRepository elementRepository;
    private final FileReader csvFileReader;

    public ElementService(ElementRepository elementRepository, FileReader csvFileReader) {
        this.elementRepository = elementRepository;
        this.csvFileReader = csvFileReader;
    }

    public String loadDataFromCsvFile(MultipartFile file) {
        try {
            var numberOfRecords = elementRepository.saveAll(csvFileReader.readFile(file.getInputStream())).size();
            return String.format("Inserted: %s records", numberOfRecords);

        } catch (IOException e) {
            throw new RuntimeException("Fail to save csv data: " + e.getMessage());
        }
    }

    public List<Element> getElemets() {
        return elementRepository.findAll();
    }
}
