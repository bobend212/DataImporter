package com.example.DataImporter.Element;

import com.example.DataImporter.DTO.ElementDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ElementMapper {

    @Mapping(target = "projectId", source = "element.project.id")
    ElementDTO elementToDTO(Element element);

}
