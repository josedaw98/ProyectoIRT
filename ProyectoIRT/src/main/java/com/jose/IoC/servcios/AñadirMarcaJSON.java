package com.jose.IoC.servcios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jose.IoC.datos.marca.Marca;
import com.jose.IoC.datos.marca.MarcaDAO;


@Service
public class AñadirMarcaJSON {

	
	
	@Autowired
	MarcaDAO marcaDAO;
	
	
	public void AñadirMarca(Marca marca) {
		
		marcaDAO.save(marca);
		
	}
}
