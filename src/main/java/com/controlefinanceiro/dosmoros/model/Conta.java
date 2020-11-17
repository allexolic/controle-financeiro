package com.controlefinanceiro.dosmoros.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;


@Entity
@DynamicUpdate
@Table(name = "conta_pagar")
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "id_tipo_conta")
	private int nmDoc;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "dt_vencimento")
	private Date dtVencimento;
	
	@NumberFormat(pattern = "#,##0.00")
	@Column(name = "vl_doc", columnDefinition="decimal(19,2) default 0")
	private double valorDoc;

	@Enumerated(EnumType.STRING)
	private StatusConta statusConta;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name="dt_pagamento")
	private Date dtPagamento;
	
	@NumberFormat(pattern = "#,##0.00")
	@Column(name = "vl_pago", columnDefinition="decimal(19,2) default 0")
	private double valorPago;
	
	@Transient
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dtVencimentoAte;
	
	@Column(name = "fl_pago", columnDefinition="int not null default '0'")
	private int flPago;
	
	@Column(name="cd_barras")
	private String codBarras;
	
	@Column(name = "id_usuario_ins")
	private Integer usuarioCadastro;
	
	@Column(name = "id_usuario_upd")
	private Integer usuarioAtualizacao;
	
	public Date getDtVencimentoAte() {
		return dtVencimentoAte;
	}

	public void setDtVencimentoAte(Date dtVencimentoAte) {
		this.dtVencimentoAte = dtVencimentoAte;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNmDoc() {
		return nmDoc;
	}

	public void setNmDoc(int nmDoc) {
		this.nmDoc = nmDoc;
	}

	public Date getDtVencimento() {
		return dtVencimento;
	}

	public void setDtVencimento(Date dtVencimento) {
		this.dtVencimento = dtVencimento;
	}

	public double getValorDoc() {
		return valorDoc;
	}

	public void setValorDoc(double valorDoc) {
		this.valorDoc = valorDoc;
	}

	public StatusConta getStatusConta() {
		return statusConta;
	}

	public void setStatusConta(StatusConta statusConta) {
		this.statusConta = statusConta;
	}

	public Date getDtPagamento() {
		return dtPagamento;
	}

	public void setDtPagamento(Date dtPagamento) {
		this.dtPagamento = dtPagamento;
	}

	public double getValorPago() {
		return valorPago;
	}

	public void setValorPago(double valorPago) {
		this.valorPago = valorPago;
	}

	public int getFlPago() {
		return flPago;
	}

	public void setFlPago(int flPago) {
		this.flPago = flPago;
	}

	public String getCodBarras() {
		return codBarras;
	}

	public void setCodBarras(String codBarras) {
		this.codBarras = codBarras;
	}

	public Integer getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(Integer usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

	public Integer getUsuarioAtualizacao() {
		return usuarioAtualizacao;
	}

	public void setUsuarioAtualizacao(Integer usuarioAtualizacao) {
		this.usuarioAtualizacao = usuarioAtualizacao;
	}

	

	
}
