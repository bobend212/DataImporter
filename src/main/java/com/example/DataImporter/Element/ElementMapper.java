package com.example.DataImporter.Element;

import com.example.DataImporter.DTO.ElementExportDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ElementMapper {

    ElementExportDTO elementToExportDTO(Element element);

}
