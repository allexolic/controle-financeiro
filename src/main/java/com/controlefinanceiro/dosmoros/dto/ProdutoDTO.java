package com.controlefinanceiro.dosmoros.dto;



public class ProdutoDTO {
	
	private Long codigo;
	private String nomeProduto;


	
	public ProdutoDTO(Long codigo, String nomeProduto) {
		//super();
		this.codigo = codigo;
		this.nomeProduto = nomeProduto;

	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	
		
}
