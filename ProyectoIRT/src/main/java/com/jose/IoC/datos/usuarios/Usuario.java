package com.jose.IoC.datos.usuarios;



import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostUpdate;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.persistence.JoinColumn;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jose.IoC.datos.Roles.Rol;
import com.jose.IoC.datos.trabajo.Trabajo;





@Entity
public class Usuario implements UserDetails  {

	


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@NotNull(message="no puedes dejar esto vacio")
	@Size(min=1, message="no puedes dejar esto vacio")
	private String usuario;
	
	@Column
	@NotNull(message="no puedes dejar esto vacio")
	@Size(min=1, message="no puedes dejar esto vacio")
	private String password;
	
	@Column
	@NotNull(message="no puedes dejar esto vacio")
	@Size(min=1, message="no puedes dejar esto vacio")
	private String nombre;
	
	@Column
	@NotNull(message="no puedes dejar esto vacio")
	@Size(min=1, message="no puedes dejar esto vacio")
	private String apellidos;


	@ManyToOne
	@JoinColumn(name="Rol_nombre")
	private Rol rol = new Rol();	

	
	

	
	
	


	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRoles(Rol rol) {
		this.rol = rol;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
	    grantedAuthorities.add(new SimpleGrantedAuthority(rol.getNombre()));
	    	    
	    return grantedAuthorities;
	}
	

	@Override
	public String getUsername() {
		return this.usuario;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String toString() {
		return "[usuario=" + usuario + "]";
	}



	
	@PreUpdate
	public void antesDeUpdate() {
		
		System.out.println(this);
	}
	
	
	@PostUpdate
	public void despuesDeUpdate() {
		
		System.out.println(this);
	}	
	
	
}
