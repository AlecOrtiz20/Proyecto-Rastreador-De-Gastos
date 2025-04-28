package com.example.ProyectoRastreador.de.Gastos.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginDTO {

    @NotBlank(message = "El email no puede estar vacio")
    private String email;

    @NotBlank(message = "La clave no puede estar vacia")
    private String clave;
}
