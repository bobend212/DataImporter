package com.example.DataImporter.domain.project.service;

import com.example.DataImporter.domain.project.dto.ProjectDTO;
import com.example.DataImporter.domain.project.mapper.ProjectMapper;
import com.example.DataImporter.domain.project.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    public ProjectService(ProjectRepository projectRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }

    public List<ProjectDTO> getAllProjects() {

        return projectRepository.findAll().stream().map(project ->
                projectMapper.projectToDTO(project).withTotalRows(project.getElements().size())).toList();

    }

}
