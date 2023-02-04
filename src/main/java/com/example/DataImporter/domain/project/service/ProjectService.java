package com.example.DataImporter.domain.project.service;

import com.example.DataImporter.domain.project.dto.ProjectCreateDTO;
import com.example.DataImporter.domain.project.dto.ProjectDTO;
import com.example.DataImporter.domain.project.dto.ProjectResponse;
import com.example.DataImporter.domain.project.entity.Project;
import com.example.DataImporter.domain.project.mapper.ProjectMapper;
import com.example.DataImporter.domain.project.repository.ProjectRepository;
import com.example.DataImporter.exception.NotFoundException;
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
                projectMapper.projectToDTO(project).withTotalRows(projectTotalElements(project))).toList();
    }

    public ProjectDTO getSingleProject(Long projectId) {
        return projectRepository.findById(projectId).map(project ->
                        projectMapper.projectToDTO(project).withTotalRows(projectTotalElements(project)))
                .orElseThrow(() -> new NotFoundException(
                        String.format("Project with ID: %s not found.", projectId)));
    }

    public ProjectResponse createProject(ProjectCreateDTO projectCreateDTO) {
        return projectMapper.projectToProjectResponse(projectRepository.save(
                projectMapper.projectCreateToProject(ProjectCreateDTO.builder()
                        .number(projectCreateDTO.getNumber())
                        .name(projectCreateDTO.getName())
                        .build())
        ));
    }

    private int projectTotalElements(Project project) {
        return project.getElements().size();
    }

}
