package com.controlefinanceiro.dosmoros.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.controlefinanceiro.dosmoros.repository.Compras;


@Controller
@RequestMapping("/dashboard")
public class DashboardController {
	
	@Autowired
	private Compras compras;
	
	@GetMapping
	public ModelAndView dashboard() {
		ModelAndView mv = new ModelAndView("/dashboard/dashboard");
		mv.addObject("totalCompras", compras.count());
		mv.addObject("totalComprasMes", compras.countPorMes());
		
		String valor = compras.sumCompras();						
		
		if (valor == null) {
			
			valor = "0,00";
		}
		
		mv.addObject("valorTotalMes", valor);
		
		return mv;
	}
	
}
