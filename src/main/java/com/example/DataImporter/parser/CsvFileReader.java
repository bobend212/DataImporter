package com.example.DataImporter.parser;

import com.example.DataImporter.Element.Element;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

@Component
public class CsvFileReader implements FileReader {

    @Override
    public List<Element> readFile(InputStream inputStream) {

        try (var fileReader = new BufferedReader(new InputStreamReader(inputStream))) {

            var format = CSVFormat.Builder.create(CSVFormat.DEFAULT)
                    .setHeader()
                    .setDelimiter(';')
                    .setIgnoreEmptyLines(true)
                    .setSkipHeaderRecord(true)
                    .build();

            var csvParser = new CSVParser(fileReader, format);

            return readDataFromRecords(csvParser);

        } catch (IOException e) {
            throw new RuntimeException("Failed to read csv file: " + e.getMessage());
        }
    }

    private List<Element> readDataFromRecords(CSVParser parser) throws IOException {

        var elements = new ArrayList<Element>();

        for (var record : parser.getRecords()) {

            elements.add(Element.builder()
                    .intExt(record.get("\uFEFFint_ext"))
                    .sqAng(record.get("sq_ang"))
                    .ref(record.get("ref"))
                    .material(record.get("material"))
                    .grade(record.get("grade"))
                    .width(parseInt(record.get("width")))
                    .height(parseInt(record.get("height")))
                    .height(parseInt(record.get("height")))
                    .length(parseInt(record.get("length")))
                    .qty(parseInt(record.get("qty")))
                    .setRef(record.get("set_ref"))
                    .jobNo(parseInt(record.get("job_no")))
                    .build());
        }

        return elements;
    }
}
