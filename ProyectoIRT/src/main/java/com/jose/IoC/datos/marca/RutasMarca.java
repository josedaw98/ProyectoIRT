package com.jose.IoC.datos.marca;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jose.IoC.datos.clientes.Cliente;
import com.jose.IoC.datos.modelos.Modelo;
import com.jose.IoC.datos.modelos.ModeloDAO;
import com.jose.IoC.datos.trabajo.Trabajo;
import com.jose.IoC.datos.vehiculos.Vehiculo;
import com.jose.IoC.servcios.ServiciosId;

@Controller
public class RutasMarca {
	
	
	@Autowired
	ServiciosId servicios;
	
	@Autowired
	MarcaDAO marcaDAO;
	
	@Autowired
	ModeloDAO modeloDAO;
	
	
	@GetMapping("/marcas")
	public ModelAndView VerTrabajos() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("Marcas");
	
		List<Marca> listaMarcas= (List<Marca>)marcaDAO.findAll();
		mav.addObject("listaMarcas",listaMarcas);
		
		return mav;
		
		
	}
	
	
	@GetMapping("/marcas/añadir")
	public ModelAndView AñadirClientes(){
		
		ModelAndView mav = new ModelAndView();		
		mav.setViewName("AñadirMarca");
		mav.addObject("marca",new Marca());
		
		return mav;
	}
	
	@PostMapping("/marcas/add")
	public ModelAndView addCliente(@ModelAttribute Marca marca, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView();
		
		if(bindingResult.hasErrors()) {
            mav.setViewName("redirect:/añadirMarca");
            System.out.println("Error de bindingResult" + bindingResult.hasErrors());
        } else {
            mav.setViewName("redirect:/marcas");
            marcaDAO.save(marca);
            System.out.println(marca);
        }
		
		return mav;
		
	}
	
	@GetMapping("/marcas/editar/{id}")
	public ModelAndView clienteEditar(@PathVariable Integer id) {

		
		
		Marca marca = marcaDAO.findById(id).get();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("EditarMarca");
		mav.addObject("marca", marca);

		return mav;
	}	
	
	@PostMapping("/marcas/editar")
	public ModelAndView clienteEditar(@ModelAttribute("marca") Marca marca,  
						BindingResult bindingResult) {
		
		
		ModelAndView mav = new ModelAndView();
		
		if (bindingResult.hasErrors()) {
			
			mav.setViewName("EditarCliente");
			
			return mav;
		}
		
		marcaDAO.save(marca);
		mav.setViewName("redirect:/marcas");
		return mav;
	}
	
	
	@GetMapping("/marcas/delete/{id}")
	public ModelAndView BorrarVehiculos(@PathVariable Integer id) {
		
		ModelAndView mav = new ModelAndView();
	     if (marcaDAO.existsById(id)) {
	    	 marcaDAO.deleteById(id);
	    	 mav.setViewName("redirect:/marcas");
	     }else {
	    	 mav.setViewName("ErrorBorrado");
	    	 mav.addObject("id", id);
	     }
		return mav;
		
	}
	


}
