package com.controlefinanceiro.dosmoros.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class RelatorioTotalContas {

	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date dtDe;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date dtAte;

	public Date getDtDe() {
		return dtDe;
	}

	public void setDtDe(Date dtDe) {
		this.dtDe = dtDe;
	}

	public Date getDtAte() {
		return dtAte;
	}

	public void setDtAte(Date dtAte) {
		this.dtAte = dtAte;
	}


	
	
}
