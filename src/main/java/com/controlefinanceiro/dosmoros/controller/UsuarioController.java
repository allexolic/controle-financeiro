package com.controlefinanceiro.dosmoros.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.controlefinanceiro.dosmoros.controller.page.PageWrapper;
import com.controlefinanceiro.dosmoros.model.Usuario;
import com.controlefinanceiro.dosmoros.repository.Permissoes;
import com.controlefinanceiro.dosmoros.repository.Usuarios;
import com.controlefinanceiro.dosmoros.service.UsuariosService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private Usuarios repUsuarios;
	
	@Autowired
	private Permissoes repPermissoes;
	
	@Autowired
	private UsuariosService servUsuarios;
	
	
	@GetMapping
	public ModelAndView usuario(Usuario usuario, @PageableDefault(size = 5) Pageable pageable,
								HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("/usuarios/listarUsuarios");
		
		String username = usuario.getUsername() == null ? "%" : usuario.getUsername();
		String nome = usuario.getNome() == null ? username : usuario.getNome();
		
		PageWrapper<Usuario> paginaWrapper = new PageWrapper<>(repUsuarios.porUsername(username, nome, pageable),
															   httpServletRequest);
		
		mv.addObject("pagina", paginaWrapper);
		
		return mv;
	}
	
	@GetMapping("/add")
	public ModelAndView cadastrar(Usuario usuario) {
		ModelAndView mv = new ModelAndView("/usuarios/cadastrarUsuario");
		mv.addObject("ListaPermissoes",repPermissoes.findAll());
		
		return mv;
	}
	
	@PostMapping("/add")
	public ModelAndView salvar(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes) {
		
		
		if(result.hasErrors()) {
			return new ModelAndView("/usuarios/listarUsuarios");		
		
		}	
		
			if(usuario.getId() == null) {
				servUsuarios.salvar(usuario);
				attributes.addFlashAttribute("messageAdd", "Usuário inserido com sucesso");
			
			} else {
				
				servUsuarios.editar(usuario);
				attributes.addFlashAttribute("messageEdit", "Usuário atualizado com sucesso");
			}
			
			
		Long codigo = usuario.getId();
		
		return new ModelAndView("redirect:/usuarios/" + codigo);
		
	
	}
	
	@RequestMapping("/{codigo}")
	public ModelAndView editar(@PathVariable(name = "codigo")Usuario usuario) {
		ModelAndView mv = new ModelAndView("/usuarios/cadastrarUsuario");
		mv.addObject("ListaPermissoes",repPermissoes.findAll());
		
		mv.addObject(usuario);
		
		return mv;
	}
	
	@RequestMapping(value = "/delUsuario/{codigo}")
	public ModelAndView excluir(@PathVariable(name= "codigo") Long id) {
		ModelAndView mv = new ModelAndView("redirect:/usuarios");
		
		servUsuarios.remover(id);
		
		return mv;
	}
}
