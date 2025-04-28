package com.example.ProyectoRastreador.de.Gastos.Services;

import com.example.ProyectoRastreador.de.Gastos.DTO.GastosDTO;
import com.example.ProyectoRastreador.de.Gastos.Entity.Gastos;
import com.example.ProyectoRastreador.de.Gastos.Enums.EstadoGasto;
import com.example.ProyectoRastreador.de.Gastos.MapStruct.GastosMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class GastosFilterService {

    private final MongoTemplate mongoTemplate;
    private final GastosMapper gastosMapper;

    public GastosFilterService(MongoTemplate mongoTemplate, GastosMapper gastosMapper) {
        this.mongoTemplate = mongoTemplate;
        this.gastosMapper = gastosMapper;
    }

    public Page<GastosDTO> getPasteWeek(String userId, int page, int size){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -7);

        Date fechaLimite = calendar.getTime();

        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId)
                .and("fechaCreacion").gt(fechaLimite)
        );

        Pageable pageable = PageRequest.of(page, size);
        query.with(pageable);

        List<Gastos> gastosList = this.mongoTemplate.find(query, Gastos.class);

        List<GastosDTO> gastosDTOList = gastosList.stream()
                .map(this.gastosMapper::toDTO)
                .toList();

        long total = this.mongoTemplate.count(query.skip(-1).limit(-1), Gastos.class);

        return new PageImpl<>(gastosDTOList, pageable, total);
    }

    public Page<GastosDTO> getLastMonth(String iduser, int page, int size){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);

        Date fechaLImite = calendar.getTime();

        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(iduser).and("fechaCrecion")
                .gte(fechaLImite)
        );

        Pageable pageable = PageRequest.of(page, size);
        query.with(pageable);

        List<Gastos> gastosList = this.mongoTemplate.find(query, Gastos.class);

        List<GastosDTO> gastosDTOS  = gastosList.stream()
                .map(this.gastosMapper::toDTO)
                .toList();

        long total = this.mongoTemplate.count(query.skip(-1).limit(-1), Gastos.class);

        return new PageImpl<>(gastosDTOS, pageable, total);
    }


    public Page<GastosDTO> allGastos(String idUser, int page, int size){

        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(idUser)
                .and("estadoGasto").ne(EstadoGasto.ELIMINADO)
        );

        Pageable pageable = PageRequest.of(page, size);
        query.with(pageable);

        List<Gastos> gastosList = this.mongoTemplate.find(query, Gastos.class);

        List<GastosDTO> gastosDTOS = gastosList.stream()
                .map(this.gastosMapper::toDTO)
                .toList();

        long total = this.mongoTemplate.count(query.skip(-1).limit(-1), Gastos.class);

        return new PageImpl<>(gastosDTOS, pageable, total);
    }


}
