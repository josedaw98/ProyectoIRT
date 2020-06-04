package com.jose.IoC.datos.clientes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jose.IoC.datos.marca.MarcaDAO;
import com.jose.IoC.datos.vehiculos.Vehiculo;
import com.jose.IoC.datos.vehiculos.VehiculoDAO;

@Controller
public class RutasClientes {

	@Autowired
	VehiculoDAO vehiculoDAO;
	
	@Autowired
	ClienteDAO clienteDAO;
	
	@Autowired
	MarcaDAO marcaDAO;
	
	
	
	@GetMapping("/clientes")
	public ModelAndView VerClientes() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("Clientes");
		/*
		 * No se añadiran clientes aqui , es innecesario
		 * 
		 * mav.addObject("cliente",new Cliente());
		 * 
		 * */
		
		List<Cliente> listaClientes= (List<Cliente>)clienteDAO.findAll();
		mav.addObject("listaClientes",listaClientes);
		
		return mav;
	}
	
	@GetMapping("/clientes/añadir")
	public ModelAndView AñadirClientes(){
		
		ModelAndView mav = new ModelAndView();		
		mav.setViewName("AñadirCliente");
		mav.addObject("cliente",new Cliente());
		
		return mav;
	}
	
	@PostMapping("/clientes/add")
	public ModelAndView addCliente(@ModelAttribute Cliente cliente, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView();
		
		if(bindingResult.hasErrors()) {
            mav.setViewName("redirect:/añadirClientes");
            System.out.println("Error de bindingResult" + bindingResult.hasErrors());
        } else {
            mav.setViewName("AñadirVehiculo");
            clienteDAO.save(cliente);
            mav.addObject("cliente",cliente);
            mav.addObject("vehiculo", new Vehiculo());
            mav.addObject("listaMarcas",marcaDAO.findAll());
            System.out.println(cliente);
        }
		
		return mav;
		
	}
	
	@GetMapping("/clientes/editar/{id}")
	public ModelAndView clienteEditar(@PathVariable String id) {

		
		
		Cliente cliente = clienteDAO.findById(id).get();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("EditarCliente");
		mav.addObject("cliente", cliente);

		return mav;
	}	
	
	@PostMapping("/clientes/editar")
	public ModelAndView clienteEditar(@ModelAttribute("cliente") Cliente cliente,  
						BindingResult bindingResult) {
		
		
		ModelAndView mav = new ModelAndView();
		
		if (bindingResult.hasErrors()) {
			
			mav.setViewName("EditarCliente");
			
			return mav;
		}
		
		clienteDAO.save(cliente);
		mav.setViewName("redirect:/clientes");
		return mav;
	}
	
	@GetMapping("/clientes/delete/{id}")
	public ModelAndView BorrarVehiculos(@PathVariable String id) {
		
		ModelAndView mav = new ModelAndView();
	     if (clienteDAO.existsById(id)) {
	    	 clienteDAO.deleteById(id);
	    	 mav.setViewName("redirect:/clientes");
	     }else {
	    	 mav.setViewName("ErrorBorrado");
	    	 mav.addObject("id", id);
	     }
		return mav;
		
	}
	
	
	
	
	
	
	
	
}



