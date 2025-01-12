package com.gml.primeraWeb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.gml.primeraWeb.repositories.PersonaRepository;

@SpringBootApplication
public class PrimeraWebApplication{
	
	public PersonaRepository personarepository;

	public static void main(String[] args) {
		SpringApplication.run(PrimeraWebApplication.class, args);
	}

}
