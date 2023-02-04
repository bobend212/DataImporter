package com.example.DataImporter.domain.element.mapper;

import com.example.DataImporter.domain.element.dto.ElementDTO;
import com.example.DataImporter.domain.element.entity.Element;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ElementMapper {

    @Mapping(target = "projectId", source = "element.project.id")
    ElementDTO elementToDTO(Element element);

}
