package com.controlefinanceiro.dosmoros.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.controlefinanceiro.dosmoros.controller.page.PageWrapper;
import com.controlefinanceiro.dosmoros.dto.FabricanteDTO;
import com.controlefinanceiro.dosmoros.model.Fabricante;
import com.controlefinanceiro.dosmoros.service.FabricantesService;

@Controller
@RequestMapping("/fabricantes")
public class FabricanteController {
	
	@Autowired
	private FabricantesService servFabricantes;

	@RequestMapping(value = "/add")
	public String adicionar(Fabricante fabricante) {
		return "/fabricantes/cadastrarFabricante";
	}
	
	@RequestMapping(value = "/add", method= RequestMethod.POST)
	public String salvar(@Valid Fabricante fabricante, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			return "/fabricantes/listarFabricantes";
		}
		
		if(fabricante.getCodigo() == null) {
			servFabricantes.salvar(fabricante);			
			attributes.addFlashAttribute("messageAdd", "Fabricante cadastrado com sucesso!");			
		}else
		{
			servFabricantes.salvar(fabricante);	
			attributes.addFlashAttribute("messageEdit", "Fabricante atualizado com sucesso!");
		}

		Long codigo = fabricante.getCodigo();	
		
		return "redirect:/fabricantes/" + codigo;
	}
	
	@RequestMapping
	public ModelAndView pesquisar(Fabricante fabricante, @PageableDefault(size = 5) Pageable pageable,
							HttpServletRequest httpServletRequest) {
		
		ModelAndView mv = new ModelAndView("/fabricantes/listarFabricantes");
		
		String nome = fabricante.getNome() == null ? "%" : fabricante.getNome();
		
		//model.addAttribute("repFabricantes", repFabricantes.porNome(nome));
		PageWrapper<Fabricante> paginaWrapper = new PageWrapper<>(servFabricantes.porNome(nome, pageable),
				                                                  httpServletRequest);
		
		mv.addObject("pagina", paginaWrapper);
		
		return mv;
	}
	
	@RequestMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Fabricante fabricante) {
		ModelAndView mv = new ModelAndView("/fabricantes/cadastrarFabricante");
		
		mv.addObject(fabricante);
		return mv;
	}
	
	@RequestMapping("/delFabricante/{codigo}")
	public String excluir(@PathVariable(name = "codigo") Long codigo) {
		
		servFabricantes.remover(codigo);
		
		return "redirect:/fabricantes";
	}
	
	@RequestMapping("/filtro")
	public @ResponseBody List<FabricanteDTO> filtradas(String nome){
		return servFabricantes.filtradas(nome);
	}
	
}
