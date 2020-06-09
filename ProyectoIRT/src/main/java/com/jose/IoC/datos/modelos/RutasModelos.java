package com.jose.IoC.datos.modelos;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jose.IoC.datos.clientes.Cliente;
import com.jose.IoC.datos.marca.Marca;
import com.jose.IoC.datos.marca.MarcaDAO;
import com.jose.IoC.datos.vehiculos.VehiculoDAO;

@Controller
public class RutasModelos {

	
	@Autowired
	ModeloDAO modeloDAO;
	
	@Autowired
	MarcaDAO marcaDAO;
	
	
	@GetMapping("/modelos")
	public ModelAndView VerModelos() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("Modelos");

		
		List<Modelo> listaModelos= (List<Modelo>)modeloDAO.findAll();
		mav.addObject("listaModelos",listaModelos);
		
		return mav;
	}
	
	@GetMapping("/modelos/add")
	public ModelAndView AñadirModelos(){
		
		ModelAndView mav = new ModelAndView();		
		mav.setViewName("AñadirModelo");
		mav.addObject("modelo",new Modelo());
		mav.addObject("listaMarcas",marcaDAO.findAll());
		
		return mav;
	}
	
	@PostMapping("/addModelos")
	public ModelAndView addModelo(@Valid @ModelAttribute Modelo modelo, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView();
		
		if(bindingResult.hasErrors()) {
            mav.setViewName("redirect:/modelos/add");
            System.out.println("Error de bindingResult" + bindingResult.hasErrors());
        } else {
            mav.setViewName("Modelos");
           modeloDAO.save(modelo);
            System.out.println(modelo);
        }
		
		return mav;
		
	}
	@GetMapping("/modelos/editar/{id}")
	public ModelAndView modeloEditar(@PathVariable Integer id) {

		
		
		Modelo modelo = modeloDAO.findById(id).get();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("EditarModelo");
		mav.addObject("modelo", modelo);
		mav.addObject("listaMarcas",marcaDAO.findAll());
		
		return mav;
	}
	
	@PostMapping("/modelos/editar")
	public ModelAndView modeloEditado(@Valid @ModelAttribute("modelo") Modelo modelo,  
						BindingResult bindingResult) {
		
		
		ModelAndView mav = new ModelAndView();
		
		if (bindingResult.hasErrors()) {
			
			mav.setViewName("EditarModelo");
			
			return mav;
		}
		
		modeloDAO.save(modelo);
		mav.setViewName("redirect:/modelos");
		return mav;
	}
	
	@GetMapping("/modelos/delete/{id}")
	public ModelAndView BorrarModelo(@PathVariable Integer id) {
		
		ModelAndView mav = new ModelAndView();
	     if (modeloDAO.existsById(id)) {
	    	 modeloDAO.deleteById(id);
	    	 mav.setViewName("redirect:/modelos");
	     }else {
	    	 mav.setViewName("ErrorBorrado");
	    	 mav.addObject("id", id);
	     }
		return mav;
		
	}
	
	
	@GetMapping("modelos/reprogramacion/{id}")
	public ModelAndView verReprogramcion(@PathVariable Integer id) {
		
		ModelAndView mav = new ModelAndView();
	     if (modeloDAO.existsById(id)) {
	    	
	    	 mav.addObject("nombre",  modeloDAO.findById(id).get().getNombre());
	    	 mav.addObject("reprogramacion",  modeloDAO.findById(id).get().getReprogramacion());
	    	 mav.setViewName("VerReprogramacion");
	     }else {
	    	 mav.setViewName("redirect:/modelos");
	    	 
	     }
		return mav;
		
	}
	
	
	
	
}
