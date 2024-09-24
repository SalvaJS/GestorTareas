package com.empresa.gestortareas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.gestortareas.dto.ListaTareasDTO;
import com.empresa.gestortareas.entity.ListaTareas;
import com.empresa.gestortareas.entity.Usuario;
import com.empresa.gestortareas.exception.ListaTareasException;
import com.empresa.gestortareas.repository.ListaTareasRepository;
import com.empresa.gestortareas.repository.UsuarioRepository;

@Service
public class TareasService {
	
	private static final Logger LOGGER = LogManager.getLogger(TareasService.class);
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ListaTareasRepository listaTareasRepository;
	
	public List<ListaTareasDTO> getListasTareas(Integer idUsuario){
		List<ListaTareasDTO> listasTareasDTO = new ArrayList<>();
		Optional<Usuario> opUsuario = usuarioRepository.findById(idUsuario);
		if (opUsuario.isPresent()) {
			List<ListaTareas> listasTareas = opUsuario.get().getListasTareas();
			for(ListaTareas l : listasTareas) {
				listasTareasDTO.add(new ListaTareasDTO(l));
			}
		}
		return listasTareasDTO;
	}
	
	public ListaTareas getListaTareas(Integer idListaTareas) {
		ListaTareas listaTareas = null;
		Optional<ListaTareas> opListaTareas = listaTareasRepository.findById(idListaTareas);
		if(opListaTareas.isPresent()) {
			listaTareas = opListaTareas.get();
		}
		return listaTareas;
	}
	
	public void crearListaTareas(Integer idUsuario, ListaTareas listaTareas) throws ListaTareasException {
		Optional<Usuario> opUsuario = usuarioRepository.findById(idUsuario);
		if(opUsuario.isPresent()) {
			Usuario usuario = opUsuario.get();
			listaTareas.setUsuario(usuario);
			listaTareasRepository.save(listaTareas);
		}else {
			LOGGER.error("Un usuario no registrado ha intentado crear una lista de tareas.");
			throw new ListaTareasException(ListaTareasException.MENSAJE_ERROR_CREAR_LISTA_TAREAS);
		}
	}
	
	public void actualizarListaTareas(ListaTareasDTO listaTareasDTO, Integer idUsuario) throws ListaTareasException {
		ListaTareas listaTareas = obtenerListaTareasComprobada(listaTareasDTO.getId(), idUsuario);
		if(listaTareas != null) {
			listaTareas.setNombre(listaTareasDTO.getNombre());
			listaTareas.setDescripcion(listaTareasDTO.getDescripcion());
			listaTareasRepository.save(listaTareas);
		}else {
			LOGGER.error("El usuario con id " + idUsuario + " ha intentado editar una lista que no es suya.");
			throw new ListaTareasException(ListaTareasException.MENSAJE_ERROR_EDITAR_LISTA_AJENA);
		}
	}
	
	public void eliminarListaTareas(Integer idListaTarea, Integer idUsuario) throws ListaTareasException {
		ListaTareas listaTareas = obtenerListaTareasComprobada(idListaTarea, idUsuario);
		if(listaTareas != null) {
			listaTareasRepository.deleteById(idListaTarea);
		}else {
			LOGGER.error("El usuario con id " + idUsuario + " ha intentado eliminar una lista que no es suya.");
			throw new ListaTareasException(ListaTareasException.MENSAJE_ERROR_ELIMINAR_LISTA_AJENA);
		}
	}
	
	// Se comprueba si el usuario tiene esa lista de tareas para evitar la eliminación o el editado de listas de otros usuarios.
	private ListaTareas obtenerListaTareasComprobada(Integer idListaTarea, Integer idUsuario) {
		ListaTareas listaTareas = null;
		Optional<ListaTareas> opListaTareas = listaTareasRepository.findById(idListaTarea);
		if(opListaTareas.isPresent()) {
			listaTareas = opListaTareas.get();
			if(listaTareas.getUsuario().getId() != idUsuario) {
				listaTareas = null;
			}
		}
		return listaTareas;
	}
}
