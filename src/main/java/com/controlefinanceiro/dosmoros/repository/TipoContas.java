package com.controlefinanceiro.dosmoros.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.controlefinanceiro.dosmoros.model.TipoConta;

@Repository
public interface TipoContas extends JpaRepository<TipoConta, Long>{

	@Query(value="select c from TipoConta c where c.ativo = '1'")
	List<TipoConta> listaTipoConta();
}
