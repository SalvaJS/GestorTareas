package com.empresa.gestortareas.repository;

import org.springframework.data.repository.CrudRepository;

import com.empresa.gestortareas.entity.Usuario;

import java.util.Optional;


public interface UsuarioRepository extends CrudRepository<Usuario, Integer>{

	public Optional<Usuario> findByEmail(String email);
	
	// para escribir queries más complejas:
//	@Query("SELECT u FROM Usuario u WHERE u.email = :email")
//	public Optional<Usuario> findByEmailJpql(String email);
	
	public Optional<Usuario> findByNombreUsuario(String nombreUsuario);
}
