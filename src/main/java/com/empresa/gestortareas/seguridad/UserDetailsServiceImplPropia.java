package com.empresa.gestortareas.seguridad;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.empresa.gestortareas.entity.Usuario;
import com.empresa.gestortareas.exception.InicioSesionFallidoException;
import com.empresa.gestortareas.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImplPropia implements UserDetailsService{
	
	private static final Logger LOGGER = LogManager.getLogger(UserDetailsServiceImplPropia.class);
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// En este caso el correo:
		Optional<Usuario> opUsuario = usuarioRepository.findByEmail(email);
		if(opUsuario.isEmpty()) {
			LOGGER.error("Se ha introducido un email incorrecto: " + email);
			throw new UsernameNotFoundException(InicioSesionFallidoException.MENSAJE_ERROR_USUARIO_NO_ENCONTRADO);
		}
		UsuarioDetails usuarioDetails = new UsuarioDetails(opUsuario.get());
		return usuarioDetails;
	}
}
