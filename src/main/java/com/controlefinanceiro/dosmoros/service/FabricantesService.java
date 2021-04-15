package com.controlefinanceiro.dosmoros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.controlefinanceiro.dosmoros.dto.FabricanteDTO;
import com.controlefinanceiro.dosmoros.model.Fabricante;
import com.controlefinanceiro.dosmoros.repository.Fabricantes;

@Service
public class FabricantesService {

	@Autowired
	private Fabricantes repFabricantes;
	
	@Transactional
	public void salvar(Fabricante fabricante) {
		repFabricantes.save(fabricante);
	}
	
	public void remover(Long codigo) {
		
		repFabricantes.deleteById(codigo);
	}
	
	public Page<Fabricante> porNome(String nome, Pageable pageable){
		return repFabricantes.porNome(nome, pageable);
	}
	
	public List<FabricanteDTO> filtradas(String nome){
		return repFabricantes.filtradas(nome);
	}
	
	public List<Fabricante> findAll() {
		return repFabricantes.findAll();
	}
}
