package com.example.DataImporter.domain.element.parser;

import com.example.DataImporter.domain.element.entity.Element;
import com.example.DataImporter.domain.project.entity.Project;

import java.io.InputStream;
import java.util.List;

public interface FileReader {
    List<Element> readFile(InputStream inputStream, Project project);
}
