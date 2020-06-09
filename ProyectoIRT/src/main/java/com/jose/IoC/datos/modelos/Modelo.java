package com.jose.IoC.datos.modelos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.jose.IoC.datos.marca.Marca;
import com.jose.IoC.datos.vehiculos.Vehiculo;

@Entity
public class Modelo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column
	@NotNull(message="no puedes dejar esto vacio")
	@Size(min=1, message="no puedes dejar esto vacio")
	private String nombre;

	@Column
	@NotNull(message="no puedes dejar esto vacio")
	@Size(min=1, message="no puedes dejar esto vacio")
	private String combustible;

	@Column
	@NotNull(message="no puedes dejar esto vacio")
	private Integer CV;

	@Column
	@NotNull(message="no puedes dejar esto vacio")
	private Integer NM;

	@Column
	private String extras;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy = "modelo" ,cascade = CascadeType.REMOVE)
	private List<Vehiculo> listaVehiculos = new ArrayList<Vehiculo>();
	
	
	
	public List<Vehiculo> getListaVehiculos() {
		return listaVehiculos;
	}

	public void setListaVehiculos(List<Vehiculo> listaVehiculos) {
		this.listaVehiculos = listaVehiculos;
	}

	public String getReprogramacion() {
		return reprogramacion;
	}

	public void setReprogramacion(String reprogramacion) {
		this.reprogramacion = reprogramacion;
	}

	@Column
	private String reprogramacion;

	@ManyToOne
	@JoinColumn(name = "marca_nombre")
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

	public String getCombustible() {
		return combustible;
	}

	public void setCombustible(String combustible) {
		this.combustible = combustible;
	}

	public Integer getCV() {
		return CV;
	}

	public void setCV(Integer cV) {
		CV = cV;
	}

	public Integer getNM() {
		return NM;
	}

	public void setNM(Integer nM) {
		NM = nM;
	}

	public String getExtras() {
		return extras;
	}

	public void setExtras(String extras) {
		this.extras = extras;
	}

	@Override
	public String toString() {
		return "Modelo [id=" + id + ", nombre=" + nombre + ", combustible=" + combustible + ", CV=" + CV + ", NM=" + NM
				+ ", extras=" + extras + ", marca=" + marca + "]";
	}

}
