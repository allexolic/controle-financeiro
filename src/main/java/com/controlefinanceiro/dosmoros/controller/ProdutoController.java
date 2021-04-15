package com.controlefinanceiro.dosmoros.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.controlefinanceiro.dosmoros.controller.page.PageWrapper;
import com.controlefinanceiro.dosmoros.dto.ProdutoDTO;
import com.controlefinanceiro.dosmoros.model.Produto;
import com.controlefinanceiro.dosmoros.service.FabricantesService;
import com.controlefinanceiro.dosmoros.service.ProdutosService;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutosService servProdutos;
	
	@Autowired
	private FabricantesService servFabricantes;
	
	/*
	@RequestMapping(value = "/listar")
	public String produtos(Produto produto, Model model) {
		
		model.addAttribute("repProdutos", repProdutos.findAll());
        model.addAttribute("listaFabricantes", repFabricantes.findAll());
		
		return "/produtos/listarProdutos";
	}
	*/
	@RequestMapping
	public String listaProduto(Produto produto, Model model, @PageableDefault(size = 5) Pageable pageable,
			                   HttpServletRequest httpServletRequest) {
		
		String nome = produto.getNomeProduto() == null ? "%" : produto.getNomeProduto();
			
		PageWrapper<Produto> paginaWrapper = new PageWrapper<>(servProdutos.porNome(nome, pageable),
                httpServletRequest);
		
		model.addAttribute("pagina", paginaWrapper);
		
		return "/produtos/listarProdutos";
	}
	
	@RequestMapping(value="/buscar")
	public String pesquisar(Produto produto, Model model, @PageableDefault(size = 5) Pageable pageable,
							@RequestParam(value="nomeProduto")String nomeProduto,
							@RequestParam(value="fabricante", required=false)Long fabricante,
							HttpServletRequest httpServletRequest) {
		
		String nome = produto.getNomeProduto() == null ? "%" : produto.getNomeProduto();
		String nomePorFabricante = produto.getNomeProduto() == null ? "-1" : produto.getNomeProduto();

		Long nomefabricante = fabricante;
		
		PageWrapper<Produto> paginaWrapper = new PageWrapper<>(servProdutos.porProdutoFabricante(nome, nomefabricante, pageable),
				                                               httpServletRequest);
		
		model.addAttribute("pagina", paginaWrapper);
		//model.addAttribute("produtoFabricante", repProdutos.porFabricante(nomePorFabricante));
		model.addAttribute("produtoFabricante", servProdutos.listarProdutoPorFabricante(nomePorFabricante));	
		
		return "/produtos/listarProdutos";
		
	}
	
	@RequestMapping(value = "/add")
	public ModelAndView adicionar(Produto produto) {
		ModelAndView mv = new ModelAndView("/produtos/cadastrarProduto");
		//mv.addObject("listaFabricantes", repFabricantes.findAll());
		
		return mv;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Produto produto, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			
			return new ModelAndView("/produtos/listarProdutos");
		}
		
			if(produto.getCodigo() == null) {
				servProdutos.salvar(produto);
				attributes.addFlashAttribute("messageAdd", "Produto cadastrado com sucesso!");
			}else {
				servProdutos.salvar(produto);
				attributes.addFlashAttribute("messageEdit", "Produto atualizado com sucesso!");
			}			
		
		Long codigo = produto.getCodigo();
		
		return new ModelAndView("redirect:/produtos/" + codigo);
	}
	
	@RequestMapping("/{codigo}")
	public ModelAndView editar(@PathVariable(name = "codigo") Produto produto) {
		ModelAndView mv = new ModelAndView("/produtos/cadastrarProduto");
		mv.addObject("listaFabricantes", servFabricantes.findAll());
		
		mv.addObject(produto);

		
		return mv;
	}
	
	
	@RequestMapping(value = "/delProduto/{codigo}")
	public ModelAndView excluir(@PathVariable(name = "codigo") Long codigo) {
		
		servProdutos.remover(codigo);
		
		return new ModelAndView("redirect:/produtos");
	}
	
	@RequestMapping("/filtro")
	public @ResponseBody List<ProdutoDTO> filtradas(String nomeProduto){
		return servProdutos.filtradas(nomeProduto);
	}
}
