package com.example.ProyectoRastreador.de.Gastos.Repository;

import com.example.ProyectoRastreador.de.Gastos.Entity.Gastos;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GastosRepository extends MongoRepository<Gastos, Long> {
}
