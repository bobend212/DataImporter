package com.example.DataImporter.Element;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.DataImporter.parser.FileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ElementService {

    private final ElementRepository elementRepository;
    private final FileReader csvFileReader;

    public ElementService(ElementRepository elementRepository, FileReader csvFileReader) {
        this.elementRepository = elementRepository;
        this.csvFileReader = csvFileReader;
    }

    public String loadFromCsvFile(MultipartFile file) {
        try {
            var numberOfRecords = elementRepository.saveAll(csvFileReader.readFile(file.getInputStream())).size();
            return String.format("Inserted: %s records", numberOfRecords);

        } catch (IOException e) {
            throw new RuntimeException("Failed to save csv file: " + e.getMessage());
        }
    }

    public String loadFromMultipleCsvFiles(MultipartFile[] files) {
        StringBuilder message = new StringBuilder();
        List<String> fileNames = new ArrayList<>();

        Arrays.stream(files).forEach(file -> {
            try {
                elementRepository.saveAll(csvFileReader.readFile(file.getInputStream()));
            } catch (IOException e) {
                throw new RuntimeException("Failed to save multiple csv files: " + e.getMessage());
            }
            fileNames.add(file.getOriginalFilename());
        });

        message.append("Uploaded the files successfully: ").append(fileNames);
        return String.format("Inserted: %s records", fileNames);

    }

    public List<Element> getAllElements() {
        return elementRepository.findAll();
    }

    public List<Element> getAllElementsByJobNo(int jobNo) {
        return elementRepository.findAll().stream().filter(record -> record.getJobNo() == jobNo).toList();
    }

}
