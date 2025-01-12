package com.gml.primeraWeb.services;

import java.util.List;

import com.gml.primeraWeb.entities.Persona;

public interface PersonaService {
	
	List<Persona> obtenerTodas();
	
	Persona obtenerPorId(Long id);
	
	Persona crearPersona(Persona persona);
	
	Persona actualizarPersona(Long id, Persona persona);
	
	void eliminarPersona(Long id);

}
