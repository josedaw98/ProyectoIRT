package com.jose.IoC.datos.vehiculos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.jose.IoC.datos.clientes.Cliente;

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
	private Cliente cliente = new Cliente();

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

	@Override
	public String toString() {
		return "Vehiculo [matricula=" + matricula + ", bastidor=" + bastidor + ", marca=" + marca + ", año=" + anio
				+ ", cliente=" + cliente + "]";
	}
	
	
	
}
