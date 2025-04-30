package com.example.ProyectoRastreador.de.Gastos.Services;

import com.example.ProyectoRastreador.de.Gastos.DTO.GastosDTO;
import com.example.ProyectoRastreador.de.Gastos.Entity.Gastos;
import com.example.ProyectoRastreador.de.Gastos.Enums.CategoriaGasto;
import com.example.ProyectoRastreador.de.Gastos.Enums.EstadoGasto;
import com.example.ProyectoRastreador.de.Gastos.InterfaceCRUD.CrudService;
import com.example.ProyectoRastreador.de.Gastos.MapStruct.GastosMapper;
import com.example.ProyectoRastreador.de.Gastos.Repository.GastosRepository;
import jakarta.mail.MessagingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class GastosService implements CrudService<Gastos, GastosDTO> {

    private final GastosRepository gastosRepository;
    private final GastosMapper gastosMapper;
    private final MailService mailService;

    public GastosService(GastosRepository gastosRepository, GastosMapper gastosMapper, MailService mailService) {
        this.gastosRepository = gastosRepository;
        this.gastosMapper = gastosMapper;
        this.mailService = mailService;
    }

    @Override
    public GastosDTO save(GastosDTO gastosDTO, Long id, String email) throws MessagingException {
        Gastos gastos = new Gastos(gastosDTO.getMonto(), new Date(), gastosDTO.getCategoriaGasto(), EstadoGasto.ACTIVO , gastosDTO.getDescripcion() , String.valueOf(id));
        this.gastosRepository.save(gastos);

        this.mailService.emailSendForTemplate(email, gastos.getDescripcion(), gastos.getCategoriaGasto());

        return gastosDTO;
    }

    @Override
    public GastosDTO update(GastosDTO gastosDTO, Long id) {

        Gastos gastoUpdate = this.getById(id);

        if (gastoUpdate == null){
            throw new RuntimeException("El gasto no existe");
        }

        gastoUpdate.setDescripcion(gastosDTO.getDescripcion());
        gastoUpdate.setMonto(gastosDTO.getMonto());
        gastoUpdate.setCategoriaGasto(gastosDTO.getCategoriaGasto());

        this.gastosRepository.save(gastoUpdate);

        return gastosDTO;
    }

    @Override
    public void delete(Long id) {
        try {
            if (id == null){
                throw new RuntimeException("El id no puede ser nulo");
            }

            Gastos gastoDelete = getById(id);
            gastoDelete.setEstadoGasto(EstadoGasto.ELIMINADO);
            this.gastosRepository.save(gastoDelete);

        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el gasto: " + e);
        }


    }

    @Override
    public Gastos getById(Long id) {
        return this.gastosRepository.findById(id).orElseThrow(() -> new RuntimeException("El gasto no existe.."));

    }

    @Override
    public Page<GastosDTO> findAll(Pageable pageable) {
        Page<Gastos> gastosPage = this.gastosRepository.findAll(pageable);

        return gastosPage.map(gastosMapper::toDTO);
    }
}
