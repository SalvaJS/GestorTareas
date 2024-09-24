package com.empresa.gestortareas.dto;

import com.empresa.gestortareas.entity.ListaTareas;

public class ListaTareasDTO {

	
	private Integer id;
	
	private String nombre;
	
	private String descripcion;
	
	public ListaTareasDTO() {
		
	}
	
	
	public ListaTareasDTO(ListaTareas listaTareas) {
		this.id = listaTareas.getId();
		this.nombre = listaTareas.getNombre();
		this.descripcion = listaTareas.getDescripcion();
	}

	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
