package com.example.DataImporter.domain.element.parser;

import com.example.DataImporter.domain.element.entity.Element;
import com.example.DataImporter.domain.element.enums.Location;
import com.example.DataImporter.domain.element.enums.Side;
import com.example.DataImporter.domain.project.entity.Project;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

@Component
public class CsvFileReader implements FileReader {

    @Override
    public List<Element> readFile(InputStream inputStream, Project project) {

        try (var fileReader = new BufferedReader(new InputStreamReader(inputStream))) {

            var format = CSVFormat.Builder.create(CSVFormat.DEFAULT)
                    .setHeader()
                    .setDelimiter(',')
                    .setIgnoreEmptyLines(true)
                    .setSkipHeaderRecord(true)
                    .build();

            var csvParser = new CSVParser(fileReader, format);

            return readDataFromRecords(csvParser, project);

        } catch (IOException e) {
            throw new RuntimeException("Failed to read csv file: " + e.getMessage());
        }
    }

    private List<Element> readDataFromRecords(CSVParser parser, Project project) throws IOException {

        var elements = new ArrayList<Element>();

        for (var record : parser.getRecords()) {

            elements.add(Element.builder()
                    .location(Location.valueOf(record.get("location")))
                    .label(record.get("label"))
                    .side(Side.valueOf(record.get("side")))
                    .material(record.get("material"))
                    .grade(record.get("grade"))
                    .width(parseInt(record.get("width")))
                    .height(parseInt(record.get("height")))
                    .length(parseInt(record.get("length")))
                    .quantity(parseInt(record.get("quantity")))
                    .level(record.get("level"))
                    .project(project)
                    .build());
        }

        return elements;
    }
}
