package com.example.DataImporter.Element;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.DataImporter.DTO.ElementDTO;

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
    public ResponseEntity<String> importOneFile(@RequestParam("file") MultipartFile file) {
        return new ResponseEntity<>(elementService.loadFromCsvFile(file), HttpStatus.CREATED);
    }

    // @PostMapping("/import-multiple")
    // public ResponseEntity<String> importMultipleFiles(@RequestParam("files")
    // MultipartFile[] files) {
    // return new ResponseEntity<>(elementService.loadFromMultipleCsvFiles(files),
    // HttpStatus.CREATED);
    // }

    @GetMapping()
    public ResponseEntity<List<ElementDTO>> getAllElements() {
        return new ResponseEntity<>(elementService.getAllElements(), HttpStatus.OK);
    }

    // @GetMapping("/{jobNumber}")
    // public ResponseEntity<List<Element>> getAllElementsByJobNumber(@PathVariable
    // int jobNumber) {
    // return new ResponseEntity<>(elementService.getAllElementsByJobNo(jobNumber),
    // HttpStatus.OK);
    // }

    @PostMapping("/save")
    public ResponseEntity<Void> saveToFile(@RequestParam String projectNumber, @RequestParam String level)
            throws IOException {
        elementService.saveToFile(projectNumber, level);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
