package com.empresa.gestortareas.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.gestortareas.dto.ListaTareasDTO;
import com.empresa.gestortareas.exception.ListaTareasException;
import com.empresa.gestortareas.seguridad.UsuarioDetails;
import com.empresa.gestortareas.service.TareasService;

@RestController
@RequestMapping("/tareasRest")
public class TareasRest {
	
	@Autowired
	private TareasService listaTareasService;
	
	@GetMapping("/getListasTareasUsuario")
	public List<ListaTareasDTO> getListasTareas(@AuthenticationPrincipal UsuarioDetails usuarioDetails) {
		return listaTareasService.getListasTareas(usuarioDetails.getId());
	}
	
	
	@GetMapping("/eliminarListaTareas")
	public String eliminarListaTareas(@RequestParam Integer idListaTarea, @AuthenticationPrincipal UsuarioDetails usuarioDetails) {
		try {
			listaTareasService.eliminarListaTareas(idListaTarea, usuarioDetails.getId());
			return "Se ha eliminado correctamente la lista.";
		} catch (ListaTareasException e) {
			return "Ha ocurrido un error al eliminar la lista.";
		}
	}
}