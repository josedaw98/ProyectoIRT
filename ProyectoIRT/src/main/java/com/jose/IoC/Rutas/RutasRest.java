package com.jose.IoC.Rutas;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jose.IoC.datos.marca.Marca;
import com.jose.IoC.datos.marca.MarcaDAO;
import com.jose.IoC.datos.modelos.Modelo;
import com.jose.IoC.datos.modelos.ModeloDAO;
import com.jose.IoC.servcios.AñadirMarcaJSON;



@RestController
public class RutasRest {
	
	@Autowired
	ModeloDAO modeloDAO;
	
	@Autowired
	MarcaDAO marcaDAO;
	
	@PutMapping("/JSON/add")
	public  ResponseEntity<Marca> JsonEmpresaPUT(@RequestBody Marca marca) {
			
		
		marcaDAO.save(marca);
		
		
		return new ResponseEntity<Marca>(marca,HttpStatus.OK);
	}

}
