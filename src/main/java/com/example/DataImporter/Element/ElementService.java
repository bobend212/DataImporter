package com.example.DataImporter.Element;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    public void saveToFile() throws IOException {

        var allElementsByJobNo = getAllElementsByJobNo(21004);

        try (
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(SAMPLE_CSV_FILE));

                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.EXCEL.withHeader());
        ) {
            csvPrinter.printRecord("int_ext", "sq_ang", "ref", "material", "grade", "width", "height", "length", "qty", "set_ref", "job_no");

            for (Element e : allElementsByJobNo) {
                csvPrinter.printRecord(e.getIntExt(), e.getSqAng(), e.getRef(), e.getMaterial(), e.getGrade(),
                        e.getWidth(), e.getHeight(), e.getLength(), e.getQty(), e.getSetRef(), e.getJobNo());
            }

            csvPrinter.flush();
        }
    }
}
