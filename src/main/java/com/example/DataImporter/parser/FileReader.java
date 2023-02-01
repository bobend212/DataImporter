package com.example.DataImporter.parser;

import com.example.DataImporter.Element.Element;
import com.example.DataImporter.Project.Project;

import java.io.InputStream;
import java.util.List;

public interface FileReader {
    List<Element> readFile(InputStream inputStream, Project project);
}
