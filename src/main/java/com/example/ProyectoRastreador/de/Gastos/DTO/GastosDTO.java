package com.example.ProyectoRastreador.de.Gastos.DTO;

import com.example.ProyectoRastreador.de.Gastos.Enums.CategoriaGasto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GastosDTO {

    private Long id;

    @NotNull(message = "El monto es obligatorio")
    private float monto;

    @NotNull(message = "La categoria obligatoria")
    private CategoriaGasto categoriaGasto;

    @NotBlank(message = "La descripcion es obligatoria")
    private String descripcion;

    public GastosDTO(float monto, CategoriaGasto categoriaGasto, String descripcion) {
        this.monto = monto;
        this.categoriaGasto = categoriaGasto;
        this.descripcion = descripcion;
    }
}
