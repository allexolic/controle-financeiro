package com.controlefinanceiro.dosmoros.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.controlefinanceiro.dosmoros.dto.FabricanteDTO;
import com.controlefinanceiro.dosmoros.model.Fabricante;

@Repository
public interface Fabricantes extends JpaRepository<Fabricante, Long> {
	
	//@Query("select f from Fabricante f where f.nome like %?1% order by f.nome")
	//List<Fabricante> porNome(String nome);
	
	@Query("select f from Fabricante f where lower(f.nome) like lower(concat('%', concat(?1,'%'))) order by f.nome")
	Page<Fabricante> porNome(String nome, Pageable pageable);

	@Query("select new com.controlefinanceiro.dosmoros.dto.FabricanteDTO(codigo, nome) from Fabricante where nome like %?1%")
	List<FabricanteDTO> filtradas(String nome);
}

