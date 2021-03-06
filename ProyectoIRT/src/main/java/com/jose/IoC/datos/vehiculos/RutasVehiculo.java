package com.jose.IoC.datos.vehiculos;

import java.util.ArrayList;
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
import com.jose.IoC.datos.marca.MarcaDAO;
import com.jose.IoC.datos.modelos.Modelo;
import com.jose.IoC.datos.modelos.ModeloDAO;

@Controller
public class RutasVehiculo {

	@Autowired
	VehiculoDAO vehiculoDAO;
	
	@Autowired
	ClienteDAO clienteDAO;
	
	@Autowired
	MarcaDAO marcaDAO;
	
	@Autowired
	ModeloDAO modeloDAO;
	
	
	@GetMapping("/vehiculos")
	public ModelAndView VerVehiculos() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("Vehiculos");
	
		List<Vehiculo> listaVehiculos= (List<Vehiculo>)vehiculoDAO.findAll();
		mav.addObject("listaVehiculos",listaVehiculos);
		
		return mav;
	}
	
	
	@PostMapping("/addVehiculos")
	public ModelAndView addPersona(@Valid @ModelAttribute Vehiculo vehiculo, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView();
		
		if(bindingResult.hasErrors()) {
            mav.setViewName("AñadirVehiculo");
			mav.addObject("listaMarcas",marcaDAO.findAll());
			mav.addObject("listaModelos",modeloDAO.findAll());
			mav.addObject("cliente",vehiculo.getCliente());
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
		mav.addObject("listaMarcas",marcaDAO.findAll());
		mav.addObject("listaModelos", modeloDAO.findAll());

		
		

		return mav;
	}	
	
	@PostMapping("/vehiculos/editar")
	public ModelAndView usuariosEditar(@Valid @ModelAttribute("vehiculo") Vehiculo vehiculo,  
						BindingResult bindingResult) {
		
		
		ModelAndView mav = new ModelAndView();
		
		if (bindingResult.hasErrors()) {
			mav.addObject("listaMarcas",marcaDAO.findAll());
			mav.addObject("listaModelos",modeloDAO.findAll());
			mav.setViewName("EditarVehiculo");
			
			return mav;
		}
		
		vehiculoDAO.save(vehiculo);
		mav.setViewName("redirect:/vehiculos");
		return mav;
	}
	
	
	@GetMapping("/vehiculos/delete/{id}")
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

	@GetMapping("/elegirCliente")
	public ModelAndView elegirCliente(){
		
		ModelAndView mav = new ModelAndView();		
		mav.setViewName("ElegirCliente");
		
		return mav;
	}
	
	
	@GetMapping("/comprobarCliente")
	public ModelAndView ComprobarCliente(){
		
		ModelAndView mav = new ModelAndView();	
		mav.addObject("cliente",new Cliente());
		mav.setViewName("ComprobarCliente");
		
		return mav;
	}
	
	@PostMapping("/clientes/Comprobado")
	public ModelAndView ClienteComprobado(@Valid @ModelAttribute Cliente cliente){
		
		ModelAndView mav = new ModelAndView();
		
		if(clienteDAO.existsById(cliente.getDni())) {
			mav.setViewName("AñadirVehiculo");
			Optional<Cliente>Lista =clienteDAO.findById(cliente.getDni());
			Cliente clientes=Lista.get();
			mav.addObject("cliente", clientes);
			mav.addObject("vehiculo", new Vehiculo());
			mav.addObject("listaMarcas",marcaDAO.findAll());
			mav.addObject("listaModelos",modeloDAO.findAll());
			System.out.println(cliente);
		}else {
			mav.setViewName("NoEncontrado");
		}
		
		
		return mav;
	}
	
}
