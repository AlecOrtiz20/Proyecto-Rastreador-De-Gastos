package com.example.ProyectoRastreador.de.Gastos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.example.ProyectoRastreador.de.Gastos.Repository")
public class ProyectoRastreadorDeGastosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoRastreadorDeGastosApplication.class, args);
	}


}
