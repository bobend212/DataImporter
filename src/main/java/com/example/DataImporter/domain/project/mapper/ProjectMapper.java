package com.example.DataImporter.domain.project.mapper;

import com.example.DataImporter.domain.project.dto.ProjectCreateUpdateDTO;
import com.example.DataImporter.domain.project.dto.ProjectDTO;
import com.example.DataImporter.domain.project.dto.ProjectResponse;
import com.example.DataImporter.domain.project.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    @Mapping(target = "totalRows", ignore = true)
    ProjectDTO projectToDTO(Project project);

    Project projectCreateUpdateToProject(ProjectCreateUpdateDTO projectCreateUpdateDTO);

    @Mapping(target = "id", source = "id")
    ProjectResponse projectToProjectResponse(Project project);
}
