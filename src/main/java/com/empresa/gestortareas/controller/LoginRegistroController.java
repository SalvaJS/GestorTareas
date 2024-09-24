package com.empresa.gestortareas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.empresa.gestortareas.constantes.Constantes;
import com.empresa.gestortareas.entity.Rol;
import com.empresa.gestortareas.entity.Usuario;
import com.empresa.gestortareas.exception.RegistroFallidoException;
import com.empresa.gestortareas.repository.RolRepository;
import com.empresa.gestortareas.seguridad.UsuarioDetails;
import com.empresa.gestortareas.service.RegistroService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LoginRegistroController {
	
	
	@Autowired
	private RegistroService registroService;
	
	@Autowired
	private RolRepository rolRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@GetMapping("/")
	public String inicio(@AuthenticationPrincipal UsuarioDetails usuarioDetails, HttpServletRequest request, Model model) {
		if(usuarioDetails == null) {
			return Constantes.VISTA_INDEX;
		}
		return Constantes.ENDPOINT_MENU_USUARIO;
	}
	

	@GetMapping("/register")
	public String registro(Model model) {
		List<Rol> roles = (List<Rol>) rolRepository.findAll();
		Usuario usuario = new Usuario();
		model.addAttribute("usuario", usuario);
		model.addAttribute("rolesCheckBox", roles);
		return Constantes.VISTA_FORMULARIO_REGISTRO;
	}
	
	@PostMapping("/register")
	public String registrarSubmit(@ModelAttribute Usuario usuario, Model model, HttpServletRequest request) {
		try {
//			String password = usuario.getPassword();
			registroService.registrar(usuario);
//			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(usuario.getEmail(), password);
//			Authentication auth = authenticationManager.authenticate(authenticationToken);
//	        SecurityContextHolder.getContext().setAuthentication(auth);
			return Constantes.ENDPOINT_INICIO;
		} catch (RegistroFallidoException e) {
			List<Rol> roles = (List<Rol>) rolRepository.findAll();
			model.addAttribute("usuario", usuario);
			model.addAttribute("rolesCheckBox", roles);
			model.addAttribute("error", e.getMessage());
			return Constantes.VISTA_FORMULARIO_REGISTRO;
		}
	}
}