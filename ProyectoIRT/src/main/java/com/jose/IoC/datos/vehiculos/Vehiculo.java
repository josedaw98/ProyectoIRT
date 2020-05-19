package com.jose.IoC.datos.vehiculos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.jose.IoC.datos.clientes.Cliente;
import com.jose.IoC.datos.trabajo.Trabajo;

@Entity
public class Vehiculo {
	
	@Id
	private String matricula;
	

	@Column
	private String bastidor;
	
	@Column
	private String marca;
	
	@Column
	private int anio;
	

	@ManyToOne
	@JoinColumn(name="cliente_dni" )
	private Cliente cliente = new Cliente();
	
	
	 @OneToMany(fetch=FetchType.EAGER, mappedBy = "vehiculo" ,cascade = CascadeType.REMOVE)
	 private List<Trabajo> listaTrabajos = new ArrayList<Trabajo>();
	 
	 

	

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getBastidor() {
		return bastidor;
	}

	public void setBastidor(String bastidor) {
		this.bastidor = bastidor;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public List<Trabajo> getListaTrabajos() {
		return listaTrabajos;
	}

	public void setListaTrabajos(List<Trabajo> listaTrabajos) {
		this.listaTrabajos = listaTrabajos;
	}

	@Override
	public String toString() {
		return "Vehiculo [matricula=" + matricula + ", bastidor=" + bastidor + ", marca=" + marca + ", anio=" + anio
				+ ", cliente=" + cliente + "]";
	}

	
	
	
	
}
