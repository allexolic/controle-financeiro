package com.controlefinanceiro.dosmoros.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;


import com.controlefinanceiro.dosmoros.model.Compra;
import com.controlefinanceiro.dosmoros.model.CompraItem;
import com.controlefinanceiro.dosmoros.repository.Compras;


@Controller
@RequestMapping("/integracaoCompras")
public class WebComprasController {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	private Compras comprasRep;
	
	@GetMapping
	public ModelAndView ConsultarCompras() {
		
		return new ModelAndView("/integracao/consultarCompras");
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView addCompras(@PathVariable(name="codigo") Compra compra, CompraItem compraItem ) {
		ModelAndView mv = new ModelAndView("/integracao/cadastrarCompra");
		
		mv.addObject(compra);
		mv.addObject(compraItem);
		//System.out.println(compra.getCodigo());
		
		return mv;
	}	
	
	@PostMapping("/salvarCompra")
	public ModelAndView salvarCompra(Compra compra, CompraItem compraItem) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		String json = restTemplate.exchange("https://apirest-financeiro.herokuapp.com/api/notafiscal/"+compra.getCodigo(),
				HttpMethod.GET, entity, String.class).getBody();
		
		
		String dsjson = json.replace(":", ": ").toLowerCase();
				
		int compraId = comprasRep.webComprasInserir(dsjson);
		
		return new ModelAndView("redirect:/compras/"+compraId);
		//return new ModelAndView("redirect:/integracaoCompras");
	}
	
}
