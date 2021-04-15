package com.controlefinanceiro.dosmoros.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.controlefinanceiro.dosmoros.model.Compra;
import com.controlefinanceiro.dosmoros.model.CompraItem;
import com.controlefinanceiro.dosmoros.repository.CompraItens;
import com.controlefinanceiro.dosmoros.repository.Compras;

@Service
public class ComprasService {
	
	@Autowired
	private Compras repCompras;
	
	@Autowired
	private CompraItens repCompraItens;
	
	public void salvar(Compra compra) {
		
		repCompras.save(compra);
		
	}


	public void itemSalvar(CompraItem compraItem) {
		
		repCompraItens.save(compraItem);

	}
	
	public void remover(Long codigo) {
		repCompras.deleteById(codigo);
	}
	
	public void removerItem(Long codigo) {
		repCompraItens.deleteById(codigo);	
		
	}
	
	public Page<Compra> porLoja(String nmEstabelecimento, int visibilidade, Pageable pageable){
		return repCompras.porLoja(nmEstabelecimento, visibilidade, pageable);
	}
	
	public long countPorMes() {
		return repCompras.countPorMes();
	}
	
	public String sumCompras() {
		return repCompras.sumCompras();
	}
	

}
