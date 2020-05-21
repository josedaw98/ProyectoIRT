package com.jose.IoC.datos.modelos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jose.IoC.datos.clientes.Cliente;
import com.jose.IoC.datos.vehiculos.VehiculoDAO;

@Controller
public class RutasModelos {

	
	@Autowired
	ModeloDAO modeloDAO;
	
	
	@GetMapping("/modelos")
	public ModelAndView VerClientes() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("Modelos");
		/*
		 * No se añadiran clientes aqui , es innecesario
		 * 
		 * mav.addObject("cliente",new Cliente());
		 * 
		 * */
		
		List<Modelo> listaModelos= (List<Modelo>)modeloDAO.findAll();
		mav.addObject("listaModelos",listaModelos);
		
		return mav;
	}
	/*
	@GetMapping("/añadirModelo")
	public ModelAndView AñadirClientes(){
		
		ModelAndView mav = new ModelAndView();		
		mav.setViewName("AñadirCliente");
		mav.addObject("cliente",new Cliente());
		
		return mav;
	}*/
	
}
