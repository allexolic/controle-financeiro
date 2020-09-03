package com.controlefinanceiro.dosmoros.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "fabricante")
public class Fabricante {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fabricante_id")
	private Long codigo;
	
	@NotNull
	@Column(name = "nome_fabricante", length = 150)
	private String nome;
	
	@NotNull
	@Column(name = "nu_cnpj", length= 20)
	private String nuCnpj;

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

	public String getNuCnpj() {
		return nuCnpj;
	}

	public void setNuCnpj(String nuCnpj) {
		this.nuCnpj = nuCnpj;
	}
	
	

}
