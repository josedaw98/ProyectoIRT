package com.jose.IoC.datos.clientes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.jose.IoC.datos.vehiculos.Vehiculo;

@Entity
public class Cliente {
	
	@Id
	@Size(min=8, message="el dni no puede ser pequeño")
	@Size(max=10, message="el dni no puede tal largo")
	@NotNull(message="no puedes dejar esto vacio")
	private String dni;
	
	@Column
	@Size(min=1, message="no puedes dejar esto vacio")
	private String nombre;
	
	@Column
	@Size(min=1, message="no puedes dejar esto vacio")
	private String apellidos;
	
	@Column
	private String direccion;
	
	@Column
	@Pattern(regexp="[A-Za-z0-9._-]+@[A-Za-z.]+",message="email invalido")
	private String correo;
	
	@Column
	private String telefono;
	
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy = "cliente" ,cascade = CascadeType.REMOVE)
	private List<Vehiculo> listaVehiculos = new ArrayList<Vehiculo>();
	
	
	
 
	public List<Vehiculo> getListaVehiculos() {
		return listaVehiculos;
	}

	public void setListaVehiculos(List<Vehiculo> listaVehiculos) {
		this.listaVehiculos = listaVehiculos;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "Cliente [dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + "]";
	}
	
	
	

}
