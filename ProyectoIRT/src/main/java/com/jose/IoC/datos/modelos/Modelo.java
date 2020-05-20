package com.jose.IoC.datos.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.jose.IoC.datos.marca.Marca;

@Entity
public class Modelo {
	

	 @Id 
	 @GeneratedValue(strategy=GenerationType.AUTO) 
	 private Integer id;
	 
	 @Column
	 private String nombre;
	 
	 @ManyToOne
	 @JoinColumn(name="marca_nombre" )
	 private Marca marca = new Marca();

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

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	@Override
	public String toString() {
		return "Modelo [id=" + id + ", nombre=" + nombre + ", marca=" + marca + "]";
	}
	 
	 

}
