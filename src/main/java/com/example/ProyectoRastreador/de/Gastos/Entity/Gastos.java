package com.example.ProyectoRastreador.de.Gastos.Entity;

import com.example.ProyectoRastreador.de.Gastos.Enums.CategoriaGasto;
import com.example.ProyectoRastreador.de.Gastos.Enums.EstadoGasto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "gastos")
@CompoundIndex(name = "")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Gastos {

    @Id
    private String id;
    private float monto;

    @Indexed(name = "idx_fecha_registro")
    private Date fechaCreacion;
    private CategoriaGasto categoriaGasto;

    private EstadoGasto estadoGasto;
    private String descripcion;

    @Indexed(name = "idx_user_id")
    private String userId;

    public Gastos(float monto, Date fechaCreacion, CategoriaGasto categoriaGasto, EstadoGasto estadoGasto, String descripcion, String userId) {
        this.monto = monto;
        this.fechaCreacion = fechaCreacion;
        this.categoriaGasto = categoriaGasto;
        this.estadoGasto = estadoGasto;
        this.descripcion = descripcion;
        this.userId = userId;
    }
}
