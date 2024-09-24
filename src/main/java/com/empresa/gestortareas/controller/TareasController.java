package com.empresa.gestortareas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.empresa.gestortareas.constantes.Constantes;
import com.empresa.gestortareas.dto.ListaTareasDTO;
import com.empresa.gestortareas.entity.ListaTareas;
import com.empresa.gestortareas.exception.ListaTareasException;
import com.empresa.gestortareas.seguridad.UsuarioDetails;
import com.empresa.gestortareas.service.TareasService;

@Controller
public class TareasController {
	
	
	
	@Autowired
	private TareasService tareasService;
	
	@GetMapping("/menuUsuario")
	public String menuUsuario() {
		return Constantes.VISTA_MENU_USUARIO;
	}

	@PostMapping("/formularioCrearListaTareas")
	public String formularioCrearListaTareas(Model model) {
		ListaTareas listaTareas = new ListaTareas();
		model.addAttribute("listaTareas", listaTareas);
		return Constantes.VISTA_FORMULARIO_CREAR_LISTA_TAREAS;
	}
	
	@PostMapping("/crearListaTareas")
	public String crearListaTareas(@ModelAttribute ListaTareas listaTareas, @AuthenticationPrincipal UsuarioDetails usuarioDetails, Model model) {
		try {
			tareasService.crearListaTareas(usuarioDetails.getId(), listaTareas);
			return Constantes.VISTA_MENU_USUARIO;
		}catch(ListaTareasException e) {
			model.addAttribute("listaTareas", listaTareas);
			model.addAttribute("error", e.getMessage());
			return Constantes.VISTA_FORMULARIO_CREAR_LISTA_TAREAS;
		}
	}
	
	@GetMapping("/formularioEditarListaTareas")
	public String formularioEditarListaTareas(@RequestParam("idLista") Integer idLista, Model model) {
		ListaTareas listaTareas = tareasService.getListaTareas(idLista);
		ListaTareasDTO listaTareasDTO = new ListaTareasDTO(listaTareas);
		model.addAttribute("listaTareasDTO", listaTareasDTO);
		return Constantes.VISTA_FORMULARIO_EDITAR_LISTA_TAREAS;
	}
	
	@PostMapping("/editarListaTareas")
	public String editarListaTareas(@ModelAttribute ListaTareasDTO listaTareasDTO, @AuthenticationPrincipal UsuarioDetails usuarioDetails, Model model) {
		try {
			tareasService.actualizarListaTareas(listaTareasDTO, usuarioDetails.getId());
			return Constantes.VISTA_MENU_USUARIO;
		} catch (ListaTareasException e) {
			model.addAttribute("error", Constantes.MENSAJE_ERROR_EDITAR_LISTA);
			return Constantes.VISTA_MENU_USUARIO;
		}
	}
}