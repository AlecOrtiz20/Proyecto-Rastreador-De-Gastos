package com.example.ProyectoRastreador.de.Gastos.Repository;

import com.example.ProyectoRastreador.de.Gastos.Entity.CredencialesUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CredentialsRepository extends JpaRepository<CredencialesUser, Long> {
    Optional<CredencialesUser> findByEmail(String email);
}
