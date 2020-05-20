package com.jose.IoC.servcios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jose.IoC.datos.marca.Marca;
import com.jose.IoC.datos.marca.MarcaDAO;
import com.jose.IoC.datos.modelos.Modelo;
import com.jose.IoC.datos.modelos.ModeloDAO;
import com.jose.IoC.datos.trabajo.Trabajo;
import com.jose.IoC.datos.trabajo.TrabajoDAO;

@Service
public class ServiciosId {
	
	@Autowired
	MarcaDAO marcaDAO;
	
	@Autowired
	ModeloDAO modeloDAO;
	
	@Autowired
	TrabajoDAO trabajoDAO;
	
	public Integer getIDMarca() {

		Integer id = 1;
		Optional<Marca> marca = marcaDAO.findById(id);
		while (marca.isPresent()) {

			id++;
			marca = marcaDAO.findById(id);
		}

		return id;

	}
	
	public Integer getIDModelo() {

		Integer id = 1;
		Optional<Modelo> modelo = modeloDAO.findById(id);
		while (modelo.isPresent()) {

			id++;
			modelo = modeloDAO.findById(id);
		}

		return id;

	}
	
	
	public Integer getIDTrabajo() {

		Integer id = 1;
		Optional<Trabajo> trabajo = trabajoDAO.findById(id);
		while (trabajo.isPresent()) {

			id++;
			trabajo = trabajoDAO.findById(id);
		}

		return id;

	}

}
