package com.jose.IoC.datos.trabajo;

 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.jose.IoC.datos.vehiculos.Vehiculo;

@Entity
public class Trabajo {

	
	 @Id 
	 @GeneratedValue(strategy=GenerationType.AUTO) 
	 private Integer id;

	 
	 @Column
	 private String titulo;
	 
	 @Column
	 private String descripcion;
	 
	 @Column
	 private String sucesos;
	 
	 @ManyToOne
	 @JoinColumn(name="vehiculo_matricula" )
	 private Vehiculo vehiculo = new Vehiculo();
	 
	 
	 
	 
	 
	 
	 public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getSucesos() {
		return sucesos;
	}

	public void setSucesos(String sucesos) {
		this.sucesos = sucesos;
	}


	 
	 
	 
	 
		@Override
		public String toString() {
			return "Trabajo [id=" + id + ", titulo=" + titulo + ", descripcion=" + descripcion + ", sucesos=" + sucesos
					+ "]";
		}
	 
}
