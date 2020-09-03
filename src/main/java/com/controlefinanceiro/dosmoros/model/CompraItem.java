package com.controlefinanceiro.dosmoros.model;

import java.math.BigDecimal;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;
import javax.persistence.Table;


import org.springframework.format.annotation.NumberFormat;

@Entity
@Table(name = "compra_produto")
public class CompraItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "compra_produto_id")
	private Long codigo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "compra_id")
	private Compra compra;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "produto_id")
	private Produto produto;
	
	@Column(name = "nu_quantidade")
	private Integer nuQuantidade;
	
	@NumberFormat(pattern = "#,##0.00")
	@Column(name = "vl_produto")
	private BigDecimal valorProduto;
	
	@NumberFormat(pattern = "#,##0.00")
	@Column(name = "vl_desconto")
	private BigDecimal valorDesconto;
	
	@NumberFormat(pattern = "#,##0.00")
	@Column(name = "vl_quilo")
	private BigDecimal valorQuilo;
	
	@Column(name = "nu_peso")
	private float peso;
	
	@NumberFormat(pattern = "#,##0.00")
	@Column(name = "vl_total_produto")
	private BigDecimal valorTotalProduto;
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Integer getNuQuantidade() {
		return nuQuantidade;
	}

	public void setNuQuantidade(Integer nuQuantidade) {
		this.nuQuantidade = nuQuantidade;
	}

	public BigDecimal getValorProduto() {
		return valorProduto;
	}

	public void setValorProduto(BigDecimal valorProduto) {
		this.valorProduto = valorProduto;
	}

	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public BigDecimal getValorQuilo() {
		return valorQuilo;
	}

	public void setValorQuilo(BigDecimal valorQuilo) {
		this.valorQuilo = valorQuilo;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public BigDecimal getValorTotalProduto() {
		return valorTotalProduto;
	}

	public void setValorTotalProduto(BigDecimal valorTotalProduto) {
		this.valorTotalProduto = valorTotalProduto;
	}
	
	
	
}

