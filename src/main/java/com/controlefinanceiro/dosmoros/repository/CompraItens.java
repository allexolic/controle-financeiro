package com.controlefinanceiro.dosmoros.repository;

//import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.controlefinanceiro.dosmoros.model.Compra;
import com.controlefinanceiro.dosmoros.model.CompraItem;
import com.controlefinanceiro.dosmoros.model.Produto;

@Repository
public interface CompraItens extends JpaRepository<CompraItem, Long>{
	
	@Query(value="select t from CompraItem t INNER JOIN FETCH Compra c on c.codigo = t.compra "
			+ " INNER JOIN FETCH Produto p on p.codigo = t.produto "
			+ " where t.compra = ?1")
	Page<CompraItem> porCompra(Compra compra, CompraItem compraItem, Produto produto, Pageable pageable);
	//List<CompraItem> porCompra(Compra compra, CompraItem compraItem, Produto produto);
	
	
	//@Query(value = "select count(*) from CompraItem t where t.codigo = ?1")
	//List<CompraItem> porItem(Long codigo);

}
