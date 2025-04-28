package com.example.ProyectoRastreador.de.Gastos.Repository;

import com.example.ProyectoRastreador.de.Gastos.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
