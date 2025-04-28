package com.example.ProyectoRastreador.de.Gastos.MapStruct;

import com.example.ProyectoRastreador.de.Gastos.DTO.GastosDTO;
import com.example.ProyectoRastreador.de.Gastos.Entity.Gastos;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GastosMapper {
    GastosDTO toDTO(Gastos gastos);
    Gastos toEntity(GastosDTO gastosDTO);
}
