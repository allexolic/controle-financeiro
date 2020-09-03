package com.controlefinanceiro.dosmoros.service;






import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controlefinanceiro.dosmoros.model.Conta;
import com.controlefinanceiro.dosmoros.repository.Contas;

@Service
public class ContasService {

	@Autowired
	private Contas contas;
	
	
	public void salvar(Conta conta) {

		contas.save(conta);
	}
	
	public void remover(Long id) {
		contas.deleteById(id);
	}
	

}
