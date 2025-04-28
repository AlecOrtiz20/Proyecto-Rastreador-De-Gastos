package com.example.ProyectoRastreador.de.Gastos.Controllers;

import com.example.ProyectoRastreador.de.Gastos.DTO.GastosDTO;
import com.example.ProyectoRastreador.de.Gastos.Entity.CredencialesUser;
import com.example.ProyectoRastreador.de.Gastos.Repository.CredentialsRepository;
import com.example.ProyectoRastreador.de.Gastos.Security.SecurityUtils;
import com.example.ProyectoRastreador.de.Gastos.Services.CurrentUserService;
import com.example.ProyectoRastreador.de.Gastos.Services.GastosFilterService;
import com.example.ProyectoRastreador.de.Gastos.Services.GastosService;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/gasto")
public class GastosController {

    private final GastosService gastosService;
    private final GastosFilterService gastosFilterService;
    private final CurrentUserService currentUserService;

    public GastosController(GastosService gastosService, GastosFilterService gastosFilterService, CurrentUserService currentUserService) {
        this.gastosService = gastosService;
        this.gastosFilterService = gastosFilterService;
        this.currentUserService = currentUserService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> gastoSave(@RequestBody GastosDTO gastosDTO){

        try{
            CredencialesUser user = this.currentUserService.getCurrentUser();
            GastosDTO gastosDTOSave = this.gastosService.save(gastosDTO,user.getUserCredenciales().getId());

            return ResponseEntity.ok(Map.of("gasto Registrado: ", gastosDTOSave));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("Eroor", "Error al crear un gasto: " + e.getMessage()));
        }


    }

    @GetMapping("/gastos")
    public ResponseEntity<?> getGastos(){
        try{

            CredencialesUser user = this.currentUserService.getCurrentUser();

            String idUser = String.valueOf(user.getUserCredenciales().getId());

            return ResponseEntity.ok(Map.of("Gastos: " , this.gastosFilterService.allGastos(idUser, 3,3)));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al traer los gastos");
        }
    }


}
