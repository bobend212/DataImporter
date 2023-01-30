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

    @PostMapping()
    public ResponseEntity<String> saveToDatabase(@RequestParam("file") MultipartFile file) {
        return new ResponseEntity<>(elementService.loadDataFromCsvFile(file), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Element>> getAllElements() {
        return new ResponseEntity<>(elementService.getElemets(), HttpStatus.OK);
    }


}
