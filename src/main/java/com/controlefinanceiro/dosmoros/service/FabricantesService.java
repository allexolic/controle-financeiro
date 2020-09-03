package com.controlefinanceiro.dosmoros.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controlefinanceiro.dosmoros.model.Fabricante;
import com.controlefinanceiro.dosmoros.repository.Fabricantes;

@Service
public class FabricantesService {

	@Autowired
	private Fabricantes repFabricantes;
	
	public void salvar(Fabricante fabricante) {
		repFabricantes.save(fabricante);
	}
	
	public void remover(Long codigo) {
		
		repFabricantes.deleteById(codigo);
	}
}
