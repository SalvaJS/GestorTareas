package com.empresa.gestortareas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.empresa.gestortareas.constantes.Constantes;
import com.empresa.gestortareas.entity.Rol;
import com.empresa.gestortareas.entity.Usuario;
import com.empresa.gestortareas.exception.RegistroFallidoException;
import com.empresa.gestortareas.repository.RolRepository;
import com.empresa.gestortareas.repository.UsuarioRepository;

@Service
public class RegistroService {
	
	private static final Logger LOGGER = LogManager.getLogger(RegistroService.class);
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RolRepository rolRepository;
	
	public void registrar(Usuario usuario) throws RegistroFallidoException {
		if(usuarioRepository.findByEmail(usuario.getEmail().toLowerCase()).isPresent()) {
			LOGGER.error(RegistroFallidoException.MENSAJE_ERROR_REGISTRO_CORREO);
			throw new RegistroFallidoException(RegistroFallidoException.MENSAJE_ERROR_REGISTRO_CORREO);
		}
		if(usuarioRepository.findByNombreUsuario(usuario.getNombreUsuario()).isPresent()) {
			LOGGER.error(RegistroFallidoException.MENSAJE_ERROR_REGISTRO_USUARIO);
			throw new RegistroFallidoException(RegistroFallidoException.MENSAJE_ERROR_REGISTRO_USUARIO);
		}
		String emailMinuscula = usuario.getEmail().toLowerCase();
		usuario.setEmail(emailMinuscula);
		String passwordHash = passwordEncoder.encode(usuario.getPassword());
		usuario.setPassword(passwordHash);
		Optional<Rol> opRol = rolRepository.findById(Constantes.ID_ROL_USER);
		if(opRol.isPresent()) {
			List<Rol> listaRoles = new ArrayList<>();
			listaRoles.add(opRol.get());
			usuario.setRoles(listaRoles);
		}
		usuarioRepository.save(usuario);
	}
}
