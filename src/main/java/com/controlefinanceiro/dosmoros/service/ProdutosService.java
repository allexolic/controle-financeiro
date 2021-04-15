package com.controlefinanceiro.dosmoros.service;


import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.controlefinanceiro.dosmoros.dto.ProdutoDTO;
import com.controlefinanceiro.dosmoros.model.Produto;
import com.controlefinanceiro.dosmoros.repository.Produtos;

@Service
public class ProdutosService {

	@Autowired
	private Produtos repProdutos;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void salvar(Produto produto) {
		
		repProdutos.save(produto);
	}

	public void detachProduto(Produto produto) {
		entityManager.detach(produto);
	}
	
	
	public Optional<Produto> findById(Long codigo){
		return repProdutos.findById(codigo);
	}
	
	public void remover(Long codigo) {
		
		repProdutos.deleteById(codigo);
	}
	
	@SuppressWarnings("rawtypes")
	public List listarProdutoPorFabricante(String produto) throws DataAccessException {
		Query qry = 
		entityManager.createNativeQuery("Select distinct fabricante_id, nome_fabricante from "
				+ " listarProduto where lower(nome_produto) like lower('%" + produto +"%')");
		
		return qry.getResultList();
	}
	
	public Page<Produto> porNome(String nomeProduto, Pageable pageable){
		return repProdutos.porNome(nomeProduto, pageable);
	}
	
	public Page<Produto> porProdutoFabricante(String nomeProduto, Long fabricante, Pageable pageable){
		return repProdutos.porProdutoFabricante(nomeProduto, fabricante, pageable);
	}
	
	public List<ProdutoDTO> filtradas(String nomeProduto){
		return repProdutos.filtradas(nomeProduto);
	}
	
	public List<String> porFabricante(@Param("produto") String nomeProduto){
		return repProdutos.porFabricante(nomeProduto);
	}
	
	public List<Produto> findAll(){
		return repProdutos.findAll();
	}
}
