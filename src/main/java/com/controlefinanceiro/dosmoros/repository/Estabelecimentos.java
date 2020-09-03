package com.controlefinanceiro.dosmoros.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.controlefinanceiro.dosmoros.model.Estabelecimento;

@Repository
public interface Estabelecimentos extends JpaRepository<Estabelecimento, Integer>{

	@Query("SELECT e FROM Estabelecimento e WHERE id = ?1")
	List<Estabelecimento> porID(Estabelecimento id);
	
	@Query("SELECT e FROM Estabelecimento e WHERE nmEstabelecimento = ?1")
	String porNmEstabelecimento(String nmEstabelecimento);
	
	@Query("SELECT e FROM Estabelecimento e ORDER BY nmEstabelecimento")
	List<Estabelecimento> findAllOrderByNome();
}
