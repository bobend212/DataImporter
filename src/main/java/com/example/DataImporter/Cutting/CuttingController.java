package com.example.DataImporter.Cutting;

import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CuttingController {

    @Autowired
    CuttingRepository cuttingRepository;

    @PostMapping("/import")
    public String importFile(@RequestParam("file") MultipartFile file) throws Exception {
        List<Cutting> cuttingList = new ArrayList<>();
        InputStream inputStream = file.getInputStream();
        CsvParserSettings settings = new CsvParserSettings();
        settings.setHeaderExtractionEnabled(true);
        settings.setDelimiterDetectionEnabled(true);
        CsvParser parser = new CsvParser(settings);
        List<Record> parseAllRecords = parser.parseAllRecords(inputStream);
        parseAllRecords.forEach(record -> {
            Cutting cutting = new Cutting();
            cutting.setId(Integer.parseInt(record.getString("id")));
            cutting.setIntExt(record.getString("int_ext"));
            cutting.setSqAng(record.getString("sq_ang"));
            cutting.setRef(record.getString("ref"));
            cutting.setMaterial(record.getString("material"));
            cutting.setGrade(record.getString("grade"));
            cutting.setWidth(Integer.parseInt(record.getString("width")));
            cutting.setHeight(Integer.parseInt(record.getString("height")));
            cutting.setLength(Integer.parseInt(record.getString("length")));
            cutting.setLeftCut(Float.parseFloat(record.getString("left_cut")));
            cutting.setRightCut(Float.parseFloat(record.getString("right_cut")));
            cutting.setQty(Integer.parseInt(record.getString("qty")));
            cutting.setSetRef(record.getString("set_ref"));
            cuttingList.add(cutting);
        });

        cuttingRepository.saveAll(cuttingList);
        return "Import successful!";
    }
}
