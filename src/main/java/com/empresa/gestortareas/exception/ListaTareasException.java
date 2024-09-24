package com.empresa.gestortareas.exception;

public class ListaTareasException extends Exception{
	
	public static final String MENSAJE_ERROR_CREAR_LISTA_TAREAS = "Ha ocurrido un error al crear una lista de tareas.";
	public static final String MENSAJE_ERROR_EDITAR_LISTA_AJENA = "Se ha intentado edita una lista ajena.";
	public static final String MENSAJE_ERROR_ELIMINAR_LISTA_AJENA = "Se ha intentado eliminar una lista ajena.";
	
	private static final long serialVersionUID = 1L;
	public ListaTareasException() {
		super();
	}
	public ListaTareasException(String mensaje) {
		super(mensaje);
	}
}
