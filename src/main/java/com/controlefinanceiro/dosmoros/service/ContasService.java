package com.controlefinanceiro.dosmoros.service;






import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	public Page<Conta> porStatus(String statusConta, String dtVencimento, String dtVencimentoAte, Long nomeConta, 
			                     Integer visibilidade, Pageable pageable){
		return contas.porStatus(statusConta, dtVencimento, dtVencimentoAte, nomeConta, visibilidade, pageable);
	}
}
