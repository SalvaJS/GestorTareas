package com.empresa.gestortareas.exception;

public class InicioSesionFallidoException extends Exception{
	
	public static final String MENSAJE_ERROR_IS_CORREO = "El correo introducido por el usuario no es correcto.";
	public static final String MENSAJE_ERROR_IS_PASSWORD = "La contraseña introducida por el usuario no es correcta.";
	public static final String MENSAJE_ERROR_USUARIO_NO_ENCONTRADO = "El usuario no se ha encontrado en la base de datos.";
	
	private static final long serialVersionUID = 1L;
	public InicioSesionFallidoException() {
		super();
	}
	public InicioSesionFallidoException(String mensaje) {
		super(mensaje);
	}
}
