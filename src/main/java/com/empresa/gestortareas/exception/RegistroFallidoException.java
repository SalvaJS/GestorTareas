package com.empresa.gestortareas.exception;

public class RegistroFallidoException extends Exception{
	
	public static final String MENSAJE_ERROR_REGISTRO_CORREO = "Existe una cuenta con el mismo email.";
	public static final String MENSAJE_ERROR_REGISTRO_USUARIO = "Existe una cuenta con el mismo nombre de usuario.";
	
	private static final long serialVersionUID = 1L;
	public RegistroFallidoException() {
		super();
	}
	public RegistroFallidoException(String mensaje) {
		super(mensaje);
	}
}
