package com.example.ProyectoRastreador.de.Gastos.DTO;

import com.example.ProyectoRastreador.de.Gastos.Enums.EstadoUser;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacio")
    private String apellido;


    private EstadoUser estadoUser;

    @NotBlank(message = "El telefono no puede ir vacio")
    private String telefono;

    @NotBlank(message = "La direccion no puede estar vacia")
    private String direccion;

    @NotBlank(message = "La fecha de nacimiento no puede estar vacia")
    private Date fechaNacimiento = new Date();
}
