package com.jose.IoC.Rutas;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jose.IoC.datos.marca.Marca;
import com.jose.IoC.datos.marca.MarcaDAO;
import com.jose.IoC.datos.modelos.Modelo;
import com.jose.IoC.datos.modelos.ModeloDAO;
import com.jose.IoC.servcios.AñadirMarcaJSON;



@RestController
@RequestMapping("/api")
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
	
	
	@GetMapping("/Modelos/{id}")

    public List<Modelo> index(@RequestBody Integer id){
		
		Optional<Marca> marcas = marcaDAO.findById(id);
		
		Marca marca = marcas.get();

           return marca.getModelos();

   } 

}
