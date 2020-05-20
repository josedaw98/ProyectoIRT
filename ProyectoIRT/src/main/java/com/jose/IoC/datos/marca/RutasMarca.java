package com.jose.IoC.datos.marca;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jose.IoC.datos.trabajo.Trabajo;
import com.jose.IoC.servcios.ServiciosId;

@Controller
public class RutasMarca {
	
	
	@Autowired
	ServiciosId servicios;
	
	@Autowired
	MarcaDAO marcaDAO;
	
	@GetMapping("/Marcas")
	public ModelAndView VerTrabajos() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("Marcas");
	
		List<Marca> listaMarcas= (List<Marca>)marcaDAO.findAll();
		mav.addObject("listaMarcas",listaMarcas);
		
		return mav;
		
		
	}

}
