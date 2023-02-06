package com.example.DataImporter.domain.project.service;

import com.example.DataImporter.domain.project.dto.ProjectCreateUpdateDTO;
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

    public ProjectResponse createProject(ProjectCreateUpdateDTO projectCreateDTO) {
        return projectMapper.projectToProjectResponse(projectRepository.save(
                projectMapper.projectCreateUpdateToProject(ProjectCreateUpdateDTO.builder()
                        .number(projectCreateDTO.getNumber())
                        .name(projectCreateDTO.getName())
                        .build())
        ));
    }

    public void deleteProject(Long projectId) {
        var findProject = projectRepository.findById(projectId)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Project with ID: %s not found.", projectId)));

        projectRepository.delete(findProject);
    }

    public ProjectDTO updateProject(Long projectId, ProjectCreateUpdateDTO projectUpdateDTO) {
        return projectRepository.findById(projectId).map(project -> {
                    project.setName(projectUpdateDTO.getName());
                    project.setNumber(projectUpdateDTO.getNumber());
                    return projectMapper.projectToDTO(
                            projectRepository.save(project)).withTotalRows(projectTotalElements(project));
                })
                .orElseThrow(() -> new NotFoundException(
                        String.format("Project with ID: %s not found.", projectId)));
    }

    private int projectTotalElements(Project project) {
        return project.getElements().size();
    }

}
