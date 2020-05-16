package com.jose.IoC.datos.vehiculos;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jose.IoC.datos.clientes.Cliente;
import com.jose.IoC.datos.clientes.ClienteDAO;

@Controller
public class RutasVehiculo {

	@Autowired
	VehiculoDAO vehiculoDAO;
	
	@Autowired
	ClienteDAO clienteDAO;
	
	
	@GetMapping("/vehiculos")
	public ModelAndView VerVehiculos() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("Vehiculos");
	
		List<Vehiculo> listaVehiculos= (List<Vehiculo>)vehiculoDAO.findAll();
		mav.addObject("listaVehiculos",listaVehiculos);
		
		return mav;
	}
	
	@GetMapping("/añadirVehiculo")
	public ModelAndView AñadirClientes(@ModelAttribute Cliente cliente){
		
		ModelAndView mav = new ModelAndView();		
		mav.setViewName("AñadirVehiculo");
		mav.addObject("vehiculo",new Vehiculo());
		
		mav.addObject("cliente",cliente);
		
		List<Cliente> listaClientes= (List<Cliente>)clienteDAO.findAll();
		mav.addObject("listaClientes",listaClientes);
		
		
		return mav;
	}
	
	@GetMapping("/elegirCliente")
	public ModelAndView elegirCliente(){
		
		ModelAndView mav = new ModelAndView();		
		mav.setViewName("ElegirCliente");
		
		return mav;
	}
	
	
	@GetMapping("/comprobarCliente")
	public ModelAndView ComprobarCliente(){
		
		ModelAndView mav = new ModelAndView();		
		mav.setViewName("ComprobarCliente");
		
		return mav;
	}
	
	@PostMapping("/clienteComprobado")
	public ModelAndView ClienteComprobado(@ModelAttribute Cliente cliente){
		
		ModelAndView mav = new ModelAndView();
		
		if(clienteDAO.existsById(cliente.getDni())) {
			mav.setViewName("DatosCliente");
			Optional<Cliente>Lista =clienteDAO.findById(cliente.getDni());
			cliente=Lista.get();
		}else {
			mav.setViewName("NoEncontrado");
		}
		
		
		return mav;
	}
	
	
	
	@PostMapping("/addVehiculo")
	public ModelAndView addPersona(@ModelAttribute Vehiculo vehiculo, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView();
		
		if(bindingResult.hasErrors()) {
            mav.setViewName("redirect:/añadirVehiculo");
            System.out.println("Error de bindingResult" + bindingResult.hasErrors());
        } else {
            mav.setViewName("redirect:/vehiculos");
            vehiculoDAO.save(vehiculo);
          
        }
		
		return mav;
		
	}
	
	
	@GetMapping("/vehiculos/editar/{id}")
	public ModelAndView usuariosEditar(@PathVariable String id) {

		
		
		Vehiculo vehiculo = vehiculoDAO.findById(id).get();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("EditarVehiculo");
		mav.addObject("vehiculo", vehiculo);

		return mav;
	}	
	
	@PostMapping("/vehiculos/editar")
	public ModelAndView usuariosEditar(@ModelAttribute("vehiculo") Vehiculo vehiculo,  
						BindingResult bindingResult) {
		
		
		ModelAndView mav = new ModelAndView();
		
		if (bindingResult.hasErrors()) {
			
			mav.setViewName("EditarVehiculo");
			
			return mav;
		}
		
		vehiculoDAO.save(vehiculo);
		mav.setViewName("redirect:/Vehiculos");
		return mav;
	}
	
	
	@GetMapping("/delete/vehiculo/{id}")
	public ModelAndView BorrarVehiculos(@PathVariable String id) {
		
		ModelAndView mav = new ModelAndView();
	     if (vehiculoDAO.existsById(id)) {
	    	 vehiculoDAO.deleteById(id);
	    	 mav.setViewName("redirect:/vehiculos");
	     }else {
	    	 mav.setViewName("ErrorBorrado");
	    	 mav.addObject("id", id);
	     }
		return mav;
		
	}
}