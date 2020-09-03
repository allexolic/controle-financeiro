package com.controlefinanceiro.dosmoros.dto;

public class FabricanteDTO {
	
	private Long codigo;
	private String nome;
	
	public FabricanteDTO(Long codigo, String nome) {
		//super();
		this.codigo = codigo;
		this.nome = nome;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	

}
