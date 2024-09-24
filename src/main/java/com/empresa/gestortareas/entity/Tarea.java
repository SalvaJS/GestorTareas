package com.empresa.gestortareas.entity;

import java.time.LocalDateTime;

import org.hibernate.type.descriptor.jdbc.OrdinalEnumJdbcType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Tarea implements Comparable<Tarea>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true)
	private Integer orden;
	
	private String nombre;
	
	private Boolean tareaResuelta;
	
	private LocalDateTime fechaCreacionDateTime;
	
	private LocalDateTime fechaFinalizacionDateTime;
	
	@ManyToOne
	private ListaTareas listaTareas;
	
	
	
	
	
	
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
	public Boolean getTareaResuelta() {
		return tareaResuelta;
	}
	public void setTareaResuelta(Boolean tareaResuelta) {
		this.tareaResuelta = tareaResuelta;
	}
	public LocalDateTime getFechaCreacionDateTime() {
		return fechaCreacionDateTime;
	}
	public void setFechaCreacionDateTime(LocalDateTime fechaCreacionDateTime) {
		this.fechaCreacionDateTime = fechaCreacionDateTime;
	}
	public LocalDateTime getFechaFinalizacionDateTime() {
		return fechaFinalizacionDateTime;
	}
	public void setFechaFinalizacionDateTime(LocalDateTime fechaFinalizacionDateTime) {
		this.fechaFinalizacionDateTime = fechaFinalizacionDateTime;
	}
	
	public ListaTareas getListaTareas() {
		return listaTareas;
	}
	
	public void setListaTareas(ListaTareas listaTareas) {
		this.listaTareas = listaTareas;
	}
	
	public Integer getOrden() {
		return orden;
	}
	
	public void setOrden(Integer orden) {
		this.orden = orden;
	}
	
	
	
	@Override
	public int compareTo(Tarea o) {
		return this.orden.compareTo(o.orden);
	}
}
