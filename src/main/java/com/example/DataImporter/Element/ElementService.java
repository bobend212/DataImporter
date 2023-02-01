package com.example.DataImporter.Element;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.DataImporter.DTO.ElementDTO;
import com.example.DataImporter.Project.ProjectRepository;
import com.example.DataImporter.exception.NotFoundException;
import com.example.DataImporter.parser.FileReader;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class ElementService {

    private static final String SAMPLE_CSV_FILE = "./sample.csv";

    @Value("${maxSize}")
    private String maxSize;

    private final ElementRepository elementRepository;
    private final ProjectRepository projectRepository;
    private final FileReader csvFileReader;
    private final ElementMapper elementMapper;

    public ElementService(ElementRepository elementRepository, ProjectRepository projectRepository,
            FileReader csvFileReader, ElementMapper elementMapper) {
        this.elementRepository = elementRepository;
        this.csvFileReader = csvFileReader;
        this.projectRepository = projectRepository;
        this.elementMapper = elementMapper;
    }

    public String loadFromCsvFile(MultipartFile file) {

        String projectNumber = file.getOriginalFilename().split("_")[0];

        if (!checkIfProjectExist(projectNumber)) {
            throw new NotFoundException(
                    String.format("Project %s does not exist in the Database.",
                            projectNumber));
        }

        try {
            var numberOfRecords = elementRepository
                    .saveAll(csvFileReader.readFile(file.getInputStream(),
                            projectRepository.findByNumber(projectNumber)))
                    .size();
            return String.format("Successfully inserted %s records for Project %s", numberOfRecords, projectNumber);

        } catch (IOException e) {
            throw new RuntimeException("Failed to import csv file: " + e.getMessage());
        }
    }

    private Boolean checkIfProjectExist(String projectNumber) {
        return projectRepository.findAll().stream()
                .anyMatch(project -> project.getNumber().equalsIgnoreCase(projectNumber));
    }

    // public String loadFromMultipleCsvFiles(MultipartFile[] files) {
    // List<String> fileNames = new ArrayList<>();

    // Arrays.stream(files).forEach(file -> {
    // try {
    // elementRepository.saveAll(csvFileReader.readFile(file.getInputStream()));
    // } catch (IOException e) {
    // throw new RuntimeException("Failed to save multiple csv files: " +
    // e.getMessage());
    // }
    // fileNames.add(file.getOriginalFilename());
    // });

    // return String.format("Inserted: %s records", fileNames);

    // }

    public List<ElementDTO> getAllElements() {
        return elementRepository.findAll().stream().map(elementMapper::elementToDTO).toList();
    }

    // public List<Element> getAllElementsByJobNo(int jobNo) {
    // return elementRepository.findAll().stream().filter(record ->
    // record.getJobNo() == jobNo).toList();
    // }

    public void saveToFile() throws IOException {

        // var allElementsByJobNo = getAllElementsByJobNo(21004);

        // try (
        // BufferedWriter writer = Files.newBufferedWriter(Paths.get(SAMPLE_CSV_FILE));

        // CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.EXCEL);
        // ) {
        // csvPrinter.printRecord("int_ext", "sq_ang", "ref", "material", "grade",
        // "width", "height", "length", "qty", "set_ref", "job_no");

        // for (Element e : allElementsByJobNo) {
        // csvPrinter.printRecord(e.getIntExt(), e.getSqAng(), e.getRef(),
        // e.getMaterial(), e.getGrade(),
        // e.getWidth(), e.getHeight(), e.getLength(), e.getQty(), e.getSetRef(),
        // e.getJobNo());
        // }

        // csvPrinter.flush();
        // }
    }
}
