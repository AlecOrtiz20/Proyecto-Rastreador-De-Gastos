package com.example.ProyectoRastreador.de.Gastos.Services;

import com.example.ProyectoRastreador.de.Gastos.Entity.CredencialesUser;
import com.example.ProyectoRastreador.de.Gastos.Repository.CredentialsRepository;
import com.example.ProyectoRastreador.de.Gastos.Security.SecurityUtils;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserService {

    private final CredentialsRepository credentialsRepository;
    private final SecurityUtils securityUtils;

    public CurrentUserService(CredentialsRepository credentialsRepository, SecurityUtils securityUtils) {
        this.credentialsRepository = credentialsRepository;
        this.securityUtils = securityUtils;
    }


    public CredencialesUser getCurrentUser(){
        String username = securityUtils.getCurrentUsername();

        System.out.println("Buscando usuario con username: " + username);

        return this.credentialsRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("El usuario no existe"));
    }
}
