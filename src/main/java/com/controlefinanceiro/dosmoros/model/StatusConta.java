package com.controlefinanceiro.dosmoros.model;

public enum StatusConta {

	PENDENTE("Pendente"),
	PAGO("Pago");
	
	private String nmStatusConta;

	StatusConta(String nmStatusConta){
		this.nmStatusConta = nmStatusConta;
	}
	
	public String getNmStatusConta() {
		return nmStatusConta;
	}
	
}
