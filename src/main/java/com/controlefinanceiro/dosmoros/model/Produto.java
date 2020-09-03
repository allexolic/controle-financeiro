package com.controlefinanceiro.dosmoros.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "produto_id")
	private Long codigo;
	
	@Column(name = "nome_produto", length = 75)
	private String nomeProduto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fabricante_id")
	private Fabricante fabricante;
	
	@OneToMany(mappedBy = "produto")
	private List<CompraItem> compras = new ArrayList<>(); 

	@Column(name = "nu_peso")
	private float nuPeso;
	
	@Column(name = "nm_medida", length = 15)
	private String medida;
	
	@Column(name = "tp_embalagem", length = 15)
	private String embalagem;
	
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

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public float getNuPeso() {
		return nuPeso;
	}

	public void setNuPeso(float nuPeso) {
		this.nuPeso = nuPeso;
	}

	public String getMedida() {
		return medida;
	}

	public void setMedida(String medida) {
		this.medida = medida;
	}

	public String getEmbalagem() {
		return embalagem;
	}

	public void setEmbalagem(String embalagem) {
		this.embalagem = embalagem;
	}
	
	
}
