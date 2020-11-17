package com.controlefinanceiro.dosmoros.controller;




import java.util.Map;
import java.util.TreeMap;

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
		
		Map<String, Integer> graphData = new TreeMap<>();
		
		graphData.put(compras.totalComprasMes().get(0).toString() +"/"+ compras.totalComprasAno().get(0), compras.totalComprasQtd().get(0));
		graphData.put(compras.totalComprasMes().get(1) +"/"+ compras.totalComprasAno().get(1), compras.totalComprasQtd().get(1));
		graphData.put(compras.totalComprasMes().get(2) +"/"+ compras.totalComprasAno().get(2), compras.totalComprasQtd().get(2));
		graphData.put(compras.totalComprasMes().get(3) +"/"+ compras.totalComprasAno().get(3), compras.totalComprasQtd().get(3));
		graphData.put(compras.totalComprasMes().get(4) +"/"+ compras.totalComprasAno().get(4), compras.totalComprasQtd().get(4));
		graphData.put(compras.totalComprasMes().get(5) +"/"+ compras.totalComprasAno().get(5), compras.totalComprasQtd().get(5));
		
		mv.addObject("chartData", graphData);
		
		Map<String, Integer> graphDataPie = new TreeMap<>();
		
		graphDataPie.put(compras.produtosMaisComprados().get(0), compras.totalProdutosMaisComprados().get(0));
		graphDataPie.put(compras.produtosMaisComprados().get(1), compras.totalProdutosMaisComprados().get(1));
		graphDataPie.put(compras.produtosMaisComprados().get(2), compras.totalProdutosMaisComprados().get(2));
		graphDataPie.put(compras.produtosMaisComprados().get(3), compras.totalProdutosMaisComprados().get(3));
		graphDataPie.put(compras.produtosMaisComprados().get(4), compras.totalProdutosMaisComprados().get(4));
		graphDataPie.put(compras.produtosMaisComprados().get(5), compras.totalProdutosMaisComprados().get(5));
		
		mv.addObject("chartDataPie", graphDataPie);
		
		
		return mv;
	}
	
}
