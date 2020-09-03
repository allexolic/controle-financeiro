package com.controlefinanceiro.dosmoros.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "estabelecimento")
public class Estabelecimento {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="nm_estabelecimento", nullable=false, length =175)
	private String nmEstabelecimento;
	
	@Column(name= "nu_cnpj", length=20)
	private String nuCnpj;
	
	@Column(name="endereco", length=255)
	private String endereco;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "estabelecimento")
	private List<Compra> compras;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNmEstabelecimento() {
		return nmEstabelecimento;
	}

	public void setNmEstabelecimento(String nmEstabelecimento) {
		this.nmEstabelecimento = nmEstabelecimento;
	}

	public String getNuCnpj() {
		return nuCnpj;
	}

	public void setNuCnpj(String nuCnpj) {
		this.nuCnpj = nuCnpj;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}
	
	
}
