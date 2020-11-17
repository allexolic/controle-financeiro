package com.controlefinanceiro.dosmoros.controller;




import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.controlefinanceiro.dosmoros.controller.page.PageWrapper;
import com.controlefinanceiro.dosmoros.model.Conta;
import com.controlefinanceiro.dosmoros.model.Documento;
import com.controlefinanceiro.dosmoros.model.StatusConta;
import com.controlefinanceiro.dosmoros.repository.Contas;
import com.controlefinanceiro.dosmoros.repository.Documentos;
import com.controlefinanceiro.dosmoros.repository.TipoContas;
import com.controlefinanceiro.dosmoros.repository.Usuarios;
import com.controlefinanceiro.dosmoros.service.ContasService;

@Controller
@RequestMapping("/contas")
public class ContaController {
	
	@Autowired
	private ContasService servContas;
	
	@Autowired
	private Contas contas;
	
	@Autowired
	private Documentos docs;
	
	@Autowired
	private TipoContas tipoContas;
	
	@Autowired
	private Usuarios usuarios;
	
	@GetMapping
	public ModelAndView viewContas(Conta conta, Principal principal, @PageableDefault(size = 5) Pageable pageable,
								   HttpServletRequest httpServletRequest) throws Exception {
		
		ModelAndView mv = new ModelAndView("/contas/listarContas");	
		
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
			Date dtVenc = conta.getDtVencimento() == null ? 
						 	sdf.parse("1900-01-01") : conta.getDtVencimento();				
			
						 	
			Date dtVencAte = conta.getDtVencimentoAte() == null ? 
							 	sdf.parse("2079-06-06") : conta.getDtVencimentoAte();
			
					 	
			String status = null;
			
			if(conta.getStatusConta() == StatusConta.PENDENTE) {
				status ="PENDENTE";
			}else if (conta.getStatusConta() == StatusConta.PAGO){
				status = "PAGO";
			}else {
				status = "";
			}
		
			Long nomeConta = (long) conta.getNmDoc();
			
			Integer idUsuario = usuarios.usuarioId(principal.getName());
			Integer idVisibilidade = usuarios.usuarioVisibilidade(idUsuario);
			
			if(idVisibilidade == 2) {
				idVisibilidade = 0;
			}else {
				idVisibilidade = idUsuario;
			}
			
		PageWrapper<Conta> pageWrapper = new PageWrapper<>(contas.porStatus(status, 
														   sdf.format(dtVenc),
														   sdf.format(dtVencAte),
														   nomeConta,
														   idVisibilidade,
														   pageable),
														   httpServletRequest);
		
		mv.addObject("pagina", pageWrapper);		
		mv.addObject("tipoConta", tipoContas.listaTipoConta());
		//System.out.println(status + " " + sdf.format(dtVenc) + " "+ sdf.format(dtVencAte) + " " + nomeConta);
		
		return mv;
	}
	
	@GetMapping("/add")
	public ModelAndView cadastrar(Conta conta) {
		ModelAndView mv = new ModelAndView("/contas/cadastrarConta");
		mv.addObject("listaStatusConta", StatusConta.values());
		mv.addObject("tipoDocumento", tipoContas.listaTipoConta());
		
		return mv;
	}
	
	@PostMapping("/add")
	public ModelAndView salvar(@Valid Conta conta, Principal principal, RedirectAttributes attributes) {
		
		Integer idUsuario;
		
		if(conta.getDtPagamento() == null) {
			conta.setFlPago(0);
		}else {
			conta.setFlPago(1);
		}
		
		if(conta.getStatusConta() == StatusConta.PAGO && conta.getDtPagamento() == null) {
			if(conta.getId() == null) {
				
				attributes.addFlashAttribute("messageErro", "Informe a data de pagamento e o valor pago.");
				
				return new ModelAndView("redirect:/contas/add");
						
			}else {
			attributes.addFlashAttribute("messageErro", "Informe a data de pagamento e o valor pago.");
			
			return new ModelAndView("redirect:/contas/" + conta.getId());
			
			}
		}
		
		idUsuario = usuarios.usuarioId(principal.getName());
		
		if(conta.getId() == null) {
			conta.setUsuarioCadastro(idUsuario);
			conta.setUsuarioAtualizacao(idUsuario);
		servContas.salvar(conta);
		
		attributes.addFlashAttribute("message", "Conta salva com sucesso.");
		} else {
			
			conta.setUsuarioAtualizacao(idUsuario);
			servContas.salvar(conta);
			attributes.addFlashAttribute("message", "Conta atualizada com sucesso.");
			
		}
		Long codigo = conta.getId();
		
		return new ModelAndView("redirect:/contas/" + codigo);
	}
	
	@RequestMapping(value = "/delConta/{id}")
	public ModelAndView excluir(@PathVariable(name = "id") Long id) {
		ModelAndView mv = new ModelAndView("redirect:/contas");
		servContas.remover(id);
		
		return mv;
	}
	
	@RequestMapping(value = "/{id}")
	public ModelAndView editar(@PathVariable(name = "id")Conta conta, Documento documento) {
		ModelAndView mv = new ModelAndView("/contas/cadastrarConta");
		
		mv.addObject("listaStatusConta",StatusConta.values());
		mv.addObject("listaDocumentos", docs.verDocs(conta.getId()));
		mv.addObject("tipoDocumento", tipoContas.listaTipoConta());
		mv.addObject(conta);
		mv.addObject(documento);		
		return mv;
	}
	
	
}
