package com.formulario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.formulario.model.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long>{

}
