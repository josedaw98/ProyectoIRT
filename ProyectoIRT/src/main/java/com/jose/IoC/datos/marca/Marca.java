package com.jose.IoC.datos.marca;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.jose.IoC.datos.modelos.Modelo;
import com.jose.IoC.datos.vehiculos.Vehiculo;

@Entity
public class Marca {
	
	 @Id 
	 @GeneratedValue(strategy=GenerationType.AUTO) 
	 private Integer id;
	 
	 @Column
	 private String nombre;
	 
	 @OneToMany(mappedBy = "marca")
	 private List<Modelo> modelos;
	 
	 @OneToMany(fetch=FetchType.EAGER, mappedBy = "marca" ,cascade = CascadeType.REMOVE)
	private List<Vehiculo> listaVehiculos = new ArrayList<Vehiculo>();

	public List<Vehiculo> getListaVehiculos() {
		return listaVehiculos;
	}

	public void setListaVehiculos(List<Vehiculo> listaVehiculos) {
		this.listaVehiculos = listaVehiculos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Modelo> getModelos() {
		return modelos;
	}

	public void setModelos(List<Modelo> modelos) {
		this.modelos = modelos;
	}

	@Override
	public String toString() {
		return "Marca [id=" + id + ", nombre=" + nombre + "]";
	}
	 
	 
	 

}
