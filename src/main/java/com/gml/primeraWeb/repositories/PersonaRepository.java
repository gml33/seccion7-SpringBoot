package com.gml.primeraWeb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gml.primeraWeb.entities.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long>{
	
}
