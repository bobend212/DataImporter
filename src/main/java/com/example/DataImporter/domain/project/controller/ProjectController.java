package com.example.DataImporter.domain.project.controller;

import com.example.DataImporter.domain.project.dto.ProjectCreateDTO;
import com.example.DataImporter.domain.project.dto.ProjectDTO;
import com.example.DataImporter.domain.project.dto.ProjectResponse;
import com.example.DataImporter.domain.project.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        return new ResponseEntity<>(projectService.getAllProjects(), HttpStatus.OK);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectDTO> getSingleProject(@PathVariable Long projectId) {
        return new ResponseEntity<>(projectService.getSingleProject(projectId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@Valid @RequestBody ProjectCreateDTO projectCreateDTO) {
        return new ResponseEntity<>(projectService.createProject(projectCreateDTO), HttpStatus.CREATED);
    }
}
