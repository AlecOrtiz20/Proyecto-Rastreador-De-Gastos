package com.example.ProyectoRastreador.de.Gastos.InterfaceCRUD;

import jakarta.mail.MessagingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CrudService<Entity, DTO>{

    DTO save(DTO dto, Long id, String email) throws MessagingException;
    DTO update(DTO dto, Long id);
    void delete(Long id);
    Entity getById(Long id);
    Page<DTO> findAll(Pageable pageable);
}
