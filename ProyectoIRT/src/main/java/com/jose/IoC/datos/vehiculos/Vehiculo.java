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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.jose.IoC.datos.clientes.Cliente;
import com.jose.IoC.datos.marca.Marca;
import com.jose.IoC.datos.modelos.Modelo;
import com.jose.IoC.datos.trabajo.Trabajo;

@Entity
public class Vehiculo {
	
	@Id
	@Size(min=6, message="la matricula no puede ser pequeño")
	@Size(max=8, message="la matricula no puede tan largo")
	@NotNull(message="no puedes dejar esto vacio")
	private String matricula;
	

	@Column
	private String bastidor;
	
	@ManyToOne
	@JoinColumn(name="marca_id" )
	private Marca marca = new Marca();
	
	@ManyToOne
	@JoinColumn(name="modelo_id" )
	private Modelo modelo = new Modelo();
	
	@Column
	private int anio;
	

	@ManyToOne
	@JoinColumn(name="cliente_dni" )
	private Cliente cliente = new Cliente();
	
	
	 @OneToMany(fetch=FetchType.EAGER, mappedBy = "vehiculo" )
	 private List<Trabajo> listaTrabajos = new ArrayList<Trabajo>();
	 
	 

	

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

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

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
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
