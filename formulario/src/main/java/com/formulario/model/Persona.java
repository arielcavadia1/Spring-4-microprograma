package com.formulario.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_PERSONA")
public class Persona{
	private long id;
	private String nombre;
	private String apellido;
	private int procesado;
	
	public Persona() {
		
		
	}
	
	public Persona(String nombre, String apellido, int procesado) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.procesado = procesado;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "nombre",nullable = false)
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Column(name = "apellido", nullable = false)
	public String getApellido() {
		return apellido;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	@Column(name = "procesado", nullable = false)
	public int getProcesado() {
		return procesado;
	}
	
	public void setProcesado(int procesado) {
		this.procesado = procesado;
	}
	
}