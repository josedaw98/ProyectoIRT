package com.jose.IoC.Rutas;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jose.IoC.datos.clientes.Cliente;

@Controller
public class RutasGenerales {
	
	
	@GetMapping("/inicio")
	public ModelAndView Inicio() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("Inicio");
		
		return mav;
	}

}
