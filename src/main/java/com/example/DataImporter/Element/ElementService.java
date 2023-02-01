package com.example.DataImporter.Element;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.DataImporter.DTO.ElementDTO;
import com.example.DataImporter.Project.Project;
import com.example.DataImporter.Project.ProjectRepository;
import com.example.DataImporter.exception.NotFoundException;
import com.example.DataImporter.parser.FileReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ElementService {

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

    public String importData(MultipartFile file) {

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

    public String importDataMultipleFiles(List<MultipartFile> files) {

        List<String> projectNumbersList = new ArrayList<>();
        files.forEach(file -> {
            projectNumbersList.add(file.getOriginalFilename().split("_")[0]);
        });

        if (!checkIfAllProjectsExist(projectNumbersList)) {
            throw new NotFoundException(
                    "One project from the specified files does not exist in the Database.");
        }

        files.forEach(file -> {
            try {
                elementRepository.saveAll(csvFileReader.readFile(file.getInputStream(),
                        projectRepository.findByNumber(file.getOriginalFilename().split("_")[0])));
            } catch (IOException e) {
                throw new RuntimeException("Failed to save multiple csv files: " +
                        e.getMessage());
            }
        });

        return String.format("Inserted records from files: %s", projectNumbersList);

    }

    public List<ElementDTO> getAllElements() {
        return elementRepository.findAll().stream().map(elementMapper::elementToDTO).toList();
    }

    public void createReportByProjectAndLevel(String projectNumber, String level) throws IOException {

        var findProjectId = projectRepository.findByNumber(projectNumber).getId();

        var findElements = elementRepository.findAll().stream()
                .filter(el -> el.getProject().getId() == findProjectId && el.getLevel().equalsIgnoreCase(level))
                .toList();

        File file = new File(
                "src/main/resources/results/" + projectNumber + "_" + level + "_"
                        + new SimpleDateFormat("yyyy-MM-dd HHmmss").format(new Date())
                        + "_result.csv");
        String absolutePath = file.getAbsolutePath();

        if (findElements.size() > 0) {
            try (
                    BufferedWriter writer = Files.newBufferedWriter(Paths.get(absolutePath));

                    CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.EXCEL);) {
                csvPrinter.printRecord("location", "label", "side", "material", "grade",
                        "width", "height", "length", "quantity", "level");

                for (Element e : findElements) {
                    csvPrinter.printRecord(e.getLocation(), e.getLabel(), e.getSide(),
                            e.getMaterial(), e.getGrade(),
                            e.getWidth(), e.getHeight(), e.getLength(), e.getQuantity(), e.getLevel());
                }

                csvPrinter.flush();
            }
        } else {
            throw new NotFoundException("Nothing to report.");
        }
    }

    private Boolean checkIfProjectExist(String projectNumber) {
        return projectRepository.findAll().stream()
                .anyMatch(project -> project.getNumber().equalsIgnoreCase(projectNumber));
    }

    private Boolean checkIfAllProjectsExist(List<String> projectNumbers) {
        var dbProjects = projectRepository.findAll().stream().map(Project::getNumber).toList();
        return projectNumbers.stream()
                .allMatch(number -> dbProjects.contains(number));
    }
}
