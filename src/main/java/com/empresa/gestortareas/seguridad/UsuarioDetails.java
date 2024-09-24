package com.empresa.gestortareas.seguridad;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.empresa.gestortareas.entity.Rol;
import com.empresa.gestortareas.entity.Usuario;

public class UsuarioDetails implements UserDetails{


	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nombreUsuario;
	private String email;
	private String password;
	private Integer edad;
	private List<Rol> roles;
	
	public UsuarioDetails() {
		
	}
	
	public UsuarioDetails(Usuario usuario) {
		this.id = usuario.getId();
		this.nombreUsuario = usuario.getNombreUsuario();
		this.email = usuario.getEmail();
		this.password = usuario.getPassword();
		this.edad = usuario.getEdad();
		this.roles = usuario.getRoles();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getNombre()))
                .collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email; // Autenticar con el email.
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
