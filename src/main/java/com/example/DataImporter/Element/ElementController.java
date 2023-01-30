package com.example.DataImporter.Element;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/elements")
public class ElementController {

    private final ElementService elementService;

    public ElementController(ElementService elementService) {
        this.elementService = elementService;
    }

    @PostMapping("/import")
    public ResponseEntity<String> importOneFile(@RequestParam("file") MultipartFile file) {
        return new ResponseEntity<>(elementService.loadFromCsvFile(file), HttpStatus.CREATED);
    }

    @PostMapping("/import-multiple")
    public ResponseEntity<String> importMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return new ResponseEntity<>(elementService.loadFromMultipleCsvFiles(files), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Element>> getAllElements() {
        return new ResponseEntity<>(elementService.getAllElements(), HttpStatus.OK);
    }

    @GetMapping("/{jobNumber}")
    public ResponseEntity<List<Element>> getAllElements(@PathVariable int jobNumber) {
        return new ResponseEntity<>(elementService.getAllElementsByJobNo(jobNumber), HttpStatus.OK);
    }


}
