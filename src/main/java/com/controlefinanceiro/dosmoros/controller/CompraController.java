package com.controlefinanceiro.dosmoros.controller;




import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.controlefinanceiro.dosmoros.controller.page.PageWrapper;
import com.controlefinanceiro.dosmoros.model.Compra;
import com.controlefinanceiro.dosmoros.model.CompraItem;
import com.controlefinanceiro.dosmoros.model.Produto;
import com.controlefinanceiro.dosmoros.repository.CompraItens;
import com.controlefinanceiro.dosmoros.repository.Estabelecimentos;
import com.controlefinanceiro.dosmoros.service.ComprasService;
import com.controlefinanceiro.dosmoros.service.ProdutosService;
import com.controlefinanceiro.dosmoros.service.UsuariosService;


@Controller
@RequestMapping("/compras")
public class CompraController {
	
	@Autowired
	private CompraItens repCompraItens;
	
	@Autowired
	private ProdutosService servProdutos;
	
	@Autowired
	private ComprasService servCompras;
	
	@Autowired
	private Estabelecimentos repEstabelecimento;
	
	@Autowired
	private UsuariosService servUsuarios;
	
	@RequestMapping
	public String listar(Compra compra, Principal principal, Model model, @PageableDefault(size = 5) Pageable pageable,
						 HttpServletRequest httpServletRequest) {
		//String nome = compra.getNmEstabelecimento() == null ? "%" : compra.getNmEstabelecimento();
	
		String nome = "%";	
		
		int idUsuario = servUsuarios.usuarioId(principal.getName());
		int visibilidade = servUsuarios.usuarioVisibilidade(idUsuario);
		
		if(visibilidade == 2) {
			visibilidade = 0;
		}else {
			visibilidade = idUsuario;
		}
		
		PageWrapper<Compra> paginaWrapper = new PageWrapper<>(servCompras.porLoja(nome, visibilidade, pageable), httpServletRequest);
		
		model.addAttribute("pagina", paginaWrapper);
				
		return "/compras/listarCompras";
	}
	
	@RequestMapping(value="/buscar")
	public String buscar(Compra compra, Principal principal, Model model, @PageableDefault(size = 5) Pageable pageable,
		    @RequestParam(value="loja")String loja,
			HttpServletRequest httpServletRequest) {
		
		String nome = loja == null ? "%" : loja;

		int idUsuario = servUsuarios.usuarioId(principal.getName());
		int visibilidade = servUsuarios.usuarioVisibilidade(idUsuario);
		
		if(visibilidade == 2) {
			visibilidade = 0;
		}else {
			visibilidade = idUsuario;
		}
		
		PageWrapper<Compra> paginaWrapper = new PageWrapper<>(servCompras.porLoja(nome, visibilidade, pageable),
															   httpServletRequest);
		
		model.addAttribute("pagina", paginaWrapper);
		System.out.println("NOME = " + loja);
		
		return "/compras/listarCompras";
	}
	
	@GetMapping(value = "/add")
	public ModelAndView adicionar(Compra compra, Principal principal) {
       
		int idUsuario = servUsuarios.usuarioId(principal.getName());
        
		compra.setValorCompra(0.00000);
		compra.setQtdItem(0);
		
		if(compra.getCodigo() == null) {
			
			compra.setUsuarioCadastro(idUsuario);
			compra.setUsuarioAtualizacao(idUsuario);	
			servCompras.salvar(compra);
			
		}else {
			compra.setUsuarioAtualizacao(idUsuario);
			servCompras.salvar(compra);
		}
						
		ModelAndView mv = new ModelAndView(new RedirectView("/compras/novaCompra/" + compra.getCodigo()));

		
		return mv;
	}
	
	@RequestMapping(value = "/novaCompra/{codigo}")
	public ModelAndView novaCompra(@PathVariable(name = "codigo") Compra compra, CompraItem compraItem, Produto produto,
			@PageableDefault(size = 5) Pageable pageable, HttpServletRequest httpServletRequest) {
		
		ModelAndView mv = new ModelAndView("compras/cadastrarCompra");

		mv.addObject(compra);
		mv.addObject(compraItem);
		mv.addObject(produto);		
		//mv.addObject("listaProdutos", repProdutos.findAll());
		mv.addObject("listaEstabelecimento", repEstabelecimento.findAllOrderByNome());
		//mv.addObject("repCompraItem", repCompraItens.porCompra(compra, compraItem, produto));
		PageWrapper<CompraItem> paginaWrapper = new PageWrapper<>(repCompraItens.porCompra(compra, 
				compraItem, produto, pageable), httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		
		return mv;
	}


	@RequestMapping(value = "/addItem/{codigo}", method = RequestMethod.POST)
	public ModelAndView addItem(@PathVariable(name = "codigo") Compra codigo, @Valid Compra compra, 
			                    Principal principal, CompraItem compraItem) {
		
		ModelAndView mv = new ModelAndView("redirect:/compras/novaCompra/"+ compra.getCodigo());
	
		mv.addObject("listaEstabelecimento", repEstabelecimento.findAllOrderByNome());
		mv.addObject("listaProdutos", servProdutos.findAll());
		mv.addObject(compra);		
		
        compraItem.setCompra(codigo);
        compraItem.setCodigo(null);       
        
		int idUsuario = servUsuarios.usuarioId(principal.getName());
		compra.setUsuarioAtualizacao(idUsuario);
				
        servCompras.salvar(compra);
        
        
        if(compraItem.getProduto() != null) {

        	servCompras.itemSalvar(compraItem);
        	
        	return mv;
        }
		
		return new ModelAndView("redirect:/compras");
	}
 
	
	@RequestMapping("/{codigo}")
	public ModelAndView editar(@PathVariable(name = "codigo") Compra compra, CompraItem compraItem, Produto produto,
							   @PageableDefault(size = 5) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("/compras/cadastrarCompra");
		
		mv.addObject("listaProdutos", servProdutos.findAll());		
		mv.addObject("listaEstabelecimento", repEstabelecimento.findAllOrderByNome());
		
		PageWrapper<CompraItem> paginaWrapper = new PageWrapper<>(repCompraItens.porCompra(compra, 
				compraItem, produto, pageable), httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		mv.addObject(compra);
		
		return mv;
	}
	
	@RequestMapping("/delCompra/{codigo}")
	public ModelAndView excluir(@PathVariable(name = "codigo") Long codigo) {
		
		servCompras.remover(codigo);
		
		return new ModelAndView("redirect:/compras");
	}
	
	@RequestMapping(value = "/delCompraItem/{codigo}")
	public ModelAndView excluirItem(@PathVariable(name = "codigo") Long codigo, @RequestParam("compra") Long codCompra) {
		
		servCompras.removerItem(codigo);

		
		return new ModelAndView("redirect:/compras/novaCompra/"+ codCompra);
	}
	

}
