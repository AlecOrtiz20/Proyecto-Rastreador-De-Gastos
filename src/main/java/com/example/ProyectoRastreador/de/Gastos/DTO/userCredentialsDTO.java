package com.example.ProyectoRastreador.de.Gastos.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Setter
@Getter
public class userCredentialsDTO {

    @NotBlank(message = "El correo no puede ir vacio")
    private String email;

    @NotBlank(message = "La clave no puede ir vacia")
    private String password;

    @NotBlank(message = "El username no puede ir vacio")
    private String username;
}
