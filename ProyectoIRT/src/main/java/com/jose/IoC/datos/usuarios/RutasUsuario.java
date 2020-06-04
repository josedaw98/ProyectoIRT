package com.jose.IoC.datos.usuarios;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.jose.IoC.datos.Roles.Rol;
import com.jose.IoC.datos.Roles.RolDAO;






@Controller
public class RutasUsuario {
	
	@Autowired
	private UsuarioDAO usuarioDAO;

	@Autowired
	private RolDAO rolDAO;	
		
	

	@GetMapping("/usuarios")
	public ModelAndView todosLosUsuarios(HttpSession sesion) {
		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("Usuarios");
		mav.addObject("user",new Usuario());
		
		List<Usuario> listaUsuarios = (List<Usuario>)usuarioDAO.findAll();
		mav.addObject("usuarios",listaUsuarios);
		
		List<Rol> listaRoles = (List<Rol>)rolDAO.findAll();
		mav.addObject("roles",listaRoles);
		

		
		return mav;
	}
	
	@GetMapping("/perfil")
	public ModelAndView perfil(HttpSession sesion) {
		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("Usuarios");
		mav.addObject("user",new Usuario());
		
		List<Usuario> listaUsuarios = (List<Usuario>)usuarioDAO.findAll();
		mav.addObject("usuarios",listaUsuarios);
		
		List<Rol> listaRoles = (List<Rol>)rolDAO.findAll();
		mav.addObject("roles",listaRoles);
		

		
		return mav;
	}
	
	
	@PostMapping("/usuarios/anadir")
	public String usuariosAnadir(@ModelAttribute @Valid Usuario usuario,
								Errors errores, ModelMap map) {
		
		
		if(errores.hasErrors()) {
			
			List<Usuario> listaUsuarios = (List<Usuario>)usuarioDAO.findAll();
			map.addAttribute("usuarios",listaUsuarios);
			
			List<Rol> listaRoles = (List<Rol>)rolDAO.findAll();
			map.addAttribute("roles",listaRoles);			
			
			map.addAttribute("user",new Usuario());
			map.addAttribute("errors",errores.getAllErrors());
			
			System.out.println(errores.getAllErrors());
			return "Usuarios";	
		}
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		
		usuarioDAO.save(usuario);
		
		return "redirect:/usuarios";
	}
	

	
	
	@PostMapping("/usuarios/editar")
	public ModelAndView usuariosEditar(
						@Valid @ModelAttribute("user") Usuario usuario,  
						BindingResult bindingResult) {
		
		
		ModelAndView mav = new ModelAndView();
		
		if (bindingResult.hasErrors()) {
			
			mav.setViewName("editarUser");
			
			List<Rol> listaRoles = (List<Rol>)rolDAO.findAll();
			mav.addObject("roles",listaRoles);
			
			return mav;
		}
		
		usuarioDAO.save(usuario);
		mav.setViewName("redirect:/usuarios");
		return mav;
	}	

	
	
	@GetMapping("/usuarios/editar/{id}")
	public ModelAndView usuariosEditar(@PathVariable String id, Authentication authentication) {
		
		
		Usuario user = usuarioDAO.findById(id).get();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("editarUser");
		mav.addObject("user",user);
		
		List<Rol> listaRoles = (List<Rol>)rolDAO.findAll();
		mav.addObject("roles",listaRoles);
		
		return mav;
	}	
	

	
	
	

	@GetMapping("/usuarios/borrar/{id}")
	public String usuariosBorrar(@PathVariable String id) {
		
		usuarioDAO.deleteById(id);
		
		return "redirect:/usuarios";
	}
	
	
	
}
