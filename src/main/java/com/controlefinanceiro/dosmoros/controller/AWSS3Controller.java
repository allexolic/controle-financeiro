package com.controlefinanceiro.dosmoros.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestPart;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.ModelAndView;

import com.controlefinanceiro.dosmoros.model.Conta;

//import com.controlefinanceiro.dosmoros.model.Documento;
import com.controlefinanceiro.dosmoros.repository.Documentos;
import com.controlefinanceiro.dosmoros.service.AWSS3Service;

@Controller
@RequestMapping(value = {"/awss3"})
public class AWSS3Controller {
	
	@Autowired
	private AWSS3Service awsS3Service;
	
	@Autowired
	private Documentos repDocs;
	
	@Value("${aws.s3.endpointUrl}")
	private String endpointUrl;

	/*
	@PostMapping(value = "/upload")
	public ResponseEntity<String> uploadFile(@RequestParam(value="file") final MultipartFile multipartFile){
		awsS3Service.uploadFile(multipartFile);
		final String response = "["+ multipartFile.getOriginalFilename() + "] vinculado com sucesso.";
		
		repDocs.saveFile(multipartFile.getOriginalFilename(), "allexolic@gmail.com", "", endpointUrl);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
	*/
	
	@PostMapping(value = "/upload")
	public String uploadFile(@RequestParam(value="file") MultipartFile multipartFile, Conta conta) {
					
		awsS3Service.uploadFile(multipartFile);

		
		String link = endpointUrl + "/ControleFinanceiro";
		
		repDocs.saveFile(multipartFile.getOriginalFilename(), link, conta.getId());
		
		return "redirect:/contas/" + conta.getId();
	}
	
	@RequestMapping(value = "/deleteFile")
	public String deleteFile(String fileName, @RequestParam("conta")Long id, Conta conta) {
		 
		 fileName = repDocs.verDocs(conta.getId());
		 //System.out.println("URL" + repDocs.url(conta.getId()));
		 
		 awsS3Service.deleteFile(fileName);
		 
		 repDocs.deleteFile(conta.getId());
		 
		return "redirect:/contas/" + conta.getId();
	}
	
}
