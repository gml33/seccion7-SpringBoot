package com.gml.primeraWeb.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gml.primeraWeb.entities.Persona;
import com.gml.primeraWeb.repositories.PersonaRepository;
import com.gml.primeraWeb.services.PersonaService;

@Service
public class PersonaServiceImpl implements PersonaService{
	
	@Autowired
	private PersonaRepository personarepository;

	@Override
	public List<Persona> obtenerTodas() {
		return personarepository.findAll();
	}

	@Override
	public Persona obtenerPorId(Long id) {
		return personarepository.findById(id).orElse(null);
		
	}

	@Override
	public Persona crearPersona(Persona persona) {
		return personarepository.save(persona);
	}

	@Override
	public Persona actualizarPersona(Long id, Persona persona) {
		Persona personaBBDD = personarepository.findById(id).orElse(null);
		
		if(personaBBDD != null) {
			personaBBDD.setNombre(persona.getNombre());
			personaBBDD.setEdad(persona.getEdad());
		}
		return personarepository.save(personaBBDD);
	}

	@Override
	public void eliminarPersona(Long id) {
		personarepository.deleteById(id);
		
	}
	
}
