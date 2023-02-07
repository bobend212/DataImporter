package com.example.DataImporter.domain.element.controller;

import com.example.DataImporter.domain.element.dto.ElementDTO;
import com.example.DataImporter.domain.element.dto.ExportResponse;
import com.example.DataImporter.domain.element.service.ElementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/elements")
public class ElementController {

    private final ElementService elementService;

    public ElementController(ElementService elementService) {
        this.elementService = elementService;
    }

    @PostMapping("/import")
    public ResponseEntity<String> importData(@RequestParam("file") MultipartFile file) {
        return new ResponseEntity<>(elementService.importData(file), HttpStatus.CREATED);
    }

    @PostMapping("/import-multiple")
    public ResponseEntity<String> importDataMultipleFiles(@RequestParam("files") List<MultipartFile> files) {
        return new ResponseEntity<>(elementService.importDataMultipleFiles(files),
                HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<ElementDTO>> getAllElements() {
        return new ResponseEntity<>(elementService.getAllElements(), HttpStatus.OK);
    }

    @GetMapping("/report")
    public ResponseEntity<ExportResponse> createReportByProjectAndLevel(@RequestParam String projectNumber,
                                                                        @RequestParam String level) throws IOException {
        return new ResponseEntity<>(elementService.createReportByProjectAndLevel(projectNumber, level), HttpStatus.OK);
    }

    @GetMapping("/report-summary/{projectNumber}")
    public ResponseEntity<ExportResponse> createReportSummary(@PathVariable String projectNumber) throws IOException {
        return new ResponseEntity<>(elementService.createReportSummary(projectNumber), HttpStatus.OK);
    }

}
