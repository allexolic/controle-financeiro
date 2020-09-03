package com.controlefinanceiro.dosmoros.repository;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.controlefinanceiro.dosmoros.dto.ProdutoDTO;
import com.controlefinanceiro.dosmoros.model.Produto;

@Repository
public interface Produtos extends JpaRepository<Produto, Long> {

	@Query(value = "select p from Produto p "
	 			 + " where lower(p.nomeProduto) like lower(concat('%', concat(?1, '%'))) "
	 			 + " order by p.nomeProduto")
	Page<Produto> porNome(String nomeProduto, Pageable pageable);

	@Query(value = "select p from Produto p "
			 + "INNER JOIN Fabricante f on f.codigo = p.fabricante "
			 + " where lower(p.nomeProduto) like lower(concat('%', concat(?1, '%'))) "
			 + " and (f.codigo = ?2 or ?2 is null)"
			 + " order by p.nomeProduto")
	Page<Produto> porProdutoFabricante(String nomeProduto, Long fabricante, Pageable pageable);
	
	//Função isnull() MSSQL  , ifnull() Mysql e nullif() PostgreSQL
	@Query("select new com.controlefinanceiro.dosmoros.dto.ProdutoDTO(p.codigo, concat(p.nomeProduto,' ', nullif(p.nuPeso,-1), nullif(coalesce(p.medida,null,''),'ND'),' - ',f.nome) ) from Produto p "
			+ " JOIN Fabricante f on f.codigo = p.fabricante "
			+ " where p.nomeProduto like %?1%")
	List<ProdutoDTO> filtradas(String nomeProduto);

	@Query(value="select distinct fabricante_id, nome_fabricante from listarProduto where lower(nome_produto) like "
			+ "lower(concat('%', concat(" + ":produto" + ", '%')))", nativeQuery=true)
	List<String> porFabricante(@Param("produto") String nomeProduto);
	
}
