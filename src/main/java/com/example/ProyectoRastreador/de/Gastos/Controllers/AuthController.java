package com.example.ProyectoRastreador.de.Gastos.Controllers;

import com.example.ProyectoRastreador.de.Gastos.DTO.LoginDTO;
import com.example.ProyectoRastreador.de.Gastos.DTO.userRequestDTO;
import com.example.ProyectoRastreador.de.Gastos.Security.JWT.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {

    private final AuthService authService;


    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody userRequestDTO userRequestDTO){
        try{

            String token = this.authService.registerUser(userRequestDTO);

            return ResponseEntity.ok(Map.of("Token: ", token));

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("Error: ", e.getMessage()));

        }

    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO){
        try{
            String token = this.authService.loginUser(loginDTO);
            return ResponseEntity.ok(Map.of("Token: " , token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("Error: ", e.getMessage()));
        }

    }


}
