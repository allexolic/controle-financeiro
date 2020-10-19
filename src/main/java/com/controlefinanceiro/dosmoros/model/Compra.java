package com.controlefinanceiro.dosmoros.model;

import java.util.ArrayList;
//import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Entity
@DynamicUpdate
@Table(name = "compra")
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "compra_id")
	private Long codigo;
	
	//@Column(name = "nm_estabelecimento", length = 75)
	//private String nmEstabelecimento;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "dt_compra")
	private Date dataCompra;		
	
	@NumberFormat(pattern = "#,##0.00")
	@Column(name = "vl_total_compra", columnDefinition = "decimal(19,2) default 0")
	private double valorCompra;
	
	@OneToMany(mappedBy="compra", cascade= CascadeType.ALL)
	List<CompraItem> compraItens = new ArrayList<>();
	
	@Column(name = "qtd_item")
	private Integer qtdItem;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="estabelecimento_id")
	private Estabelecimento estabelecimento;
	
	@Column(name="id_usuario_ins")
	private int usuarioCadastro;
	
	@Column(name="id_usuario_upd")
	private int usuarioAtualizacao;
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	//public String getNmEstabelecimento() {
	//	return nmEstabelecimento;
	//}

	//public void setNmEstabelecimento(String nmEstabelecimento) {
	//	this.nmEstabelecimento = nmEstabelecimento;
	//}

	public Date getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}

	public double getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(double valorCompra) {
		this.valorCompra = valorCompra;
	}

	public Integer getQtdItem() {
		return qtdItem;
	}

	public void setQtdItem(Integer qtdItem) {
		this.qtdItem = qtdItem;
	}

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	
	public int getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(int usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

	public int getUsuarioAtualizacao() {
		return usuarioAtualizacao;
	}

	public void setUsuarioAtualizacao(int usuarioAtualizacao) {
		this.usuarioAtualizacao = usuarioAtualizacao;
	}

	@Override
	public String toString() {
		return "Compra [codigo=" + codigo + ", dataCompra=" + dataCompra + ", valorCompra=" + valorCompra
				+ ", compraItens=" + compraItens + ", qtdItem=" + qtdItem + ", estabelecimento=" + estabelecimento
				+ ", usuarioCadastro=" + usuarioCadastro + ", usuarioAtualizacao=" + usuarioAtualizacao + "]";
	}	
	
	
	
}
