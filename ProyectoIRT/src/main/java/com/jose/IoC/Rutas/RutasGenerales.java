package com.jose.IoC.Rutas;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
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
	
	@GetMapping("/logout")
	public String finalizar(Authentication authentication) {
		
		return "Login";
	}
	
	@GetMapping("/login")
	public String seguridad(HttpSession sesion) {
		
		
		return "Login";
	}

}
