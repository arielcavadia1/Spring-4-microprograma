package com.formulario.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formulario.exception.ResourceNotFoundException;
import com.formulario.model.Persona;
import com.formulario.repository.PersonaRepository;

@CrossOrigin(origins = "http://192.168.64.2", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class PersonaController {
		
	@Autowired
	private PersonaRepository personaRepository;
	
	@GetMapping("/personas")
	public List <Persona> getAllPersonas(){
		return personaRepository.findAll();
	}
	
	
	@GetMapping("/personas/{id}")
	public ResponseEntity < Persona > getPersonaById(@PathVariable(value = "id") Long personaId)
    throws ResourceNotFoundException {
        Persona persona = personaRepository.findById(personaId)
            .orElseThrow(() -> new ResourceNotFoundException("No existe una persona con el id :: " + personaId));
        return ResponseEntity.ok().body(persona);
    } 
	
	@PostMapping("/personas")
	public Persona createPersona(@Valid @RequestBody Persona persona) {
		return personaRepository.save(persona);
	}
	
	@PutMapping("/personas/{id}")
	public ResponseEntity < Persona > updatePersona(@PathVariable(value = "id") Long personaId,
        @Valid @RequestBody Persona personaDetails) throws ResourceNotFoundException {
        Persona persona = personaRepository.findById(personaId)
            .orElseThrow(() -> new ResourceNotFoundException("No encontramos una persona con el id :: " + personaId));
        persona.setProcesado(1);
        persona.setApellido(personaDetails.getApellido());
        persona.setNombre(personaDetails.getNombre());
        final Persona updatedPersona = personaRepository.save(persona);
        return ResponseEntity.ok(updatedPersona);
    }
	
	@DeleteMapping("/personas/{id}")
    public Map < String, Boolean > deletePersona(@PathVariable(value = "id") Long personaId)
    throws ResourceNotFoundException {
        Persona persona = personaRepository.findById(personaId)
            .orElseThrow(() -> new ResourceNotFoundException("No encontramos una persona con ese id:: " + personaId));

        personaRepository.delete(persona);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
	
}
