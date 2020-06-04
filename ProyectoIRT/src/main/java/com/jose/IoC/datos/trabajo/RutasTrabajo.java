package com.jose.IoC.datos.trabajo;

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

import com.jose.IoC.datos.clientes.Cliente;
import com.jose.IoC.datos.clientes.ClienteDAO;
import com.jose.IoC.datos.vehiculos.Vehiculo;
import com.jose.IoC.datos.vehiculos.VehiculoDAO;
import com.jose.IoC.servcios.ServiciosId;

@Controller
public class RutasTrabajo {

	@Autowired
	TrabajoDAO trabajoDAO;
	
	@Autowired
	VehiculoDAO vehiculoDAO;
	
	@Autowired
	ServiciosId servicios;
	
	
	@GetMapping("/trabajos")
	public ModelAndView VerTrabajos() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("Trabajos");
	
		List<Trabajo> listaTrabajos= (List<Trabajo>)trabajoDAO.findAll();
		mav.addObject("listaTrabajos",listaTrabajos);
		
		return mav;
		
		
	}
	
	
	
	@PostMapping("/trabajos/add")
	public ModelAndView addPersona(@ModelAttribute Trabajo trabajo, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView();
		
		if(bindingResult.hasErrors()) {
            mav.setViewName("redirect:/elegirVehiculo");
            System.out.println("Error de bindingResult" + bindingResult.hasErrors());
        } else {
            mav.setViewName("redirect:/trabajos");
            trabajo.setId(servicios.getIDTrabajo());
            trabajoDAO.save(trabajo);
          
        }
		
		return mav;
		
	}
	
	@GetMapping("/elegirVehiculo")
	public ModelAndView elegirCliente(){
		
		ModelAndView mav = new ModelAndView();		
		mav.setViewName("ElegirVehiculo");
		
		return mav;
	}
	
	
	@GetMapping("/comprobarVehiculo")
	public ModelAndView ComprobarCliente(){
		
		ModelAndView mav = new ModelAndView();	
		mav.addObject("vehiculo",new Vehiculo());
		mav.setViewName("ComprobarVehiculo");
		
		return mav;
	}
	
	@PostMapping("/vehiculos/comprobado")
	public ModelAndView ClienteComprobado(@ModelAttribute Vehiculo vehiculo){
		
		ModelAndView mav = new ModelAndView();
		
		if(vehiculoDAO.existsById(vehiculo.getMatricula())) {
			mav.setViewName("AñadirTrabajo");
			Optional<Vehiculo>Lista =vehiculoDAO.findById(vehiculo.getMatricula());
			vehiculo=Lista.get();
			mav.addObject("vehiculo", vehiculo);
			mav.addObject("trabajo", new Trabajo());
			System.out.println(vehiculo);
		}else {
			mav.setViewName("NoEncontradoVehiculo");
		}
		
		
		return mav;
	}
	
	@GetMapping("/trabajos/editar/{id}")
	public ModelAndView trabajoEditar(@PathVariable Integer id) {

		
		
		Trabajo trabajo = trabajoDAO.findById(id).get();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("EditarTrabajo");
		mav.addObject("trabajo", trabajo);

		return mav;
	}	
	
	@PostMapping("/trabajos/editar")
	public ModelAndView usuariosEditar(@ModelAttribute("trabajo") Trabajo trabajo,  
						BindingResult bindingResult) {
		
		
		ModelAndView mav = new ModelAndView();
		
		if (bindingResult.hasErrors()) {
			
			mav.setViewName("EditarTrabajo");
			
			return mav;
		}
		
		trabajoDAO.save(trabajo);
		mav.setViewName("redirect:/trabajos");
		return mav;
	}
	
	@GetMapping("/trabajos/delete/{id}")
	public ModelAndView BorrarVehiculos(@PathVariable Integer id) {
		
		ModelAndView mav = new ModelAndView();
	     if (trabajoDAO.existsById(id)) {
	    	 trabajoDAO.deleteById(id);
	    	 mav.setViewName("redirect:/trabajos");
	     }else {
	    	 mav.setViewName("ErrorBorrado");
	    	 mav.addObject("id", id);
	     }
		return mav;
		
	}
	
}
