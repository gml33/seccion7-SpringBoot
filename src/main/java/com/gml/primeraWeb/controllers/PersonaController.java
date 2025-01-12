package com.gml.primeraWeb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gml.primeraWeb.entities.Persona;
import com.gml.primeraWeb.services.PersonaService;

@Controller
@RequestMapping("/personas")
public class PersonaController {
	
	@Autowired
	private PersonaService personaservice;
	
	@GetMapping
	public String listarPersonas(Model model) {
		List<Persona> personas = personaservice.obtenerTodas();
		model.addAttribute("personas", personas);
		return "listar";
	}
	
	@GetMapping("/nueva")
	public String mostrarFormularioNuevaPersona(Model model) {
		model.addAttribute("persona",new Persona());
		model.addAttribute("accion", "/personas/nueva");
		return "formulario";
	}
	
	@PostMapping("/nueva")
	public String guardarNuevaPersona(@ModelAttribute Persona persona) {
		personaservice.crearPersona(persona);
		return "redirect:/personas";
	}
	
	@GetMapping("/editar/{id}")
	public String mostrarFormularioEditarPersona(@PathVariable Long id, @ModelAttribute Persona persona, Model model) {
		model.addAttribute("persona", persona);
		model.addAttribute("accion", "/personas/editar/"+id);
		return "formulario";
	}
	
	@PostMapping("/editar/{id}")
	public String actualizarPersona(@PathVariable Long id, @ModelAttribute Persona persona) {
		personaservice.actualizarPersona(id, persona);
		return "redirect:/personas";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminarPersona(@PathVariable Long id) {
		personaservice.eliminarPersona(id);
		return "redirect:/personas";
	}
}
