package com.controlefinanceiro.dosmoros.repository;


//import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.controlefinanceiro.dosmoros.model.Conta;
//import com.controlefinanceiro.dosmoros.model.StatusConta;

@Repository
public interface Contas extends JpaRepository<Conta, Long>{

	//@Query(value = "select c from Conta c where (statusConta = ?1 or ?1 is null) and dtVencimento = ?2")
	//Page<Conta> porStatus(StatusConta statusConta, String dtVencimento, Pageable pageable);
	
	@Query(value = "select id, dt_vencimento,id_tipo_conta, nm_conta, vl_doc, status_conta, dt_pagamento, vl_pago, fl_pago, "
			+ " cd_barras, id_usuario_ins, id_usuario_upd from spListarContas (:status, :dtVencimento, :dtVencimentoAte, :nomeConta, "
			+ " :visibilidade)" ,
		   nativeQuery=true)	
	Page<Conta> porStatus(@Param("status")String statusConta, @Param("dtVencimento")String dtVencimento, 
			              @Param("dtVencimentoAte")String dtVencimentoAte, @Param("nomeConta")Long nomeConta, 
			              @Param("visibilidade")Integer visibilidade, Pageable pageable);
	

}
