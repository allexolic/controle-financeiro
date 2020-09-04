package com.controlefinanceiro.dosmoros.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.controlefinanceiro.dosmoros.dto.RelatorioTotalContas;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Controller
@RequestMapping("/relatorios")
public class RelatorioController {

	@Autowired
	private DataSource dataSource;
	
	@GetMapping("/totalContas")
	public ModelAndView relTotalContas (RelatorioTotalContas relatorioTotalContas) {
		
		return new ModelAndView("relatorios/totalContas");
	}
	
	@PostMapping("/totalContas")
	public void gerarRelatorioTotalContas(RelatorioTotalContas relatorioTotalContas, HttpServletResponse response) 
				throws JRException, SQLException, IOException {
		
		Map<String, Object> params = new HashMap<>();
		
		params.put("format", "pdf");
		params.put("dtDe", relatorioTotalContas.getDtDe());
		params.put("dtAte", relatorioTotalContas.getDtAte());
		try {
			InputStream jasperStream = this.getClass().getResourceAsStream("/reports/relatorio_total_contas.jasper");
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource.getConnection());
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "inline; filename=relatorio_total_contas.pdf");
			System.out.println("Data" + relatorioTotalContas.getDtDe());
			
			final OutputStream outputStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
		
		}catch(JRException e) {
			
			e.printStackTrace();
		}
	}
}
